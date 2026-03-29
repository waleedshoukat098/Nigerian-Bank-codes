package com.techinnovation.nigerianbankcodes.core.billing

import android.app.Activity
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchasesParams
import com.android.billingclient.api.queryProductDetails
import com.android.billingclient.api.queryPurchasesAsync
import com.techinnovation.nigerianbankcodes.core.domain.repository.PreferencesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayBillingManager @Inject constructor(
    private val billingClient: BillingClient,
    private val preferencesRepository: PreferencesRepository
) : PremiumManager {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _entitlement = MutableStateFlow(PremiumEntitlement(false, EntitlementSource.NONE))
    override val entitlement = _entitlement.asStateFlow()

    private val _catalog = MutableStateFlow(BillingCatalog())
    override val catalog = _catalog.asStateFlow()

    init {
        startConnection()
    }

    override suspend fun refresh() {
        queryProductCatalog()
        refreshEntitlementFromPlay()
    }

    override suspend fun restorePurchases() {
        refreshEntitlementFromPlay()
    }

    override fun launchPurchase(activity: Activity, plan: PremiumPlan) {
        val product = when (plan) {
            PremiumPlan.MONTHLY -> catalog.value.monthly
            PremiumPlan.YEARLY -> catalog.value.yearly
        } ?: return

        val offerToken = product.subscriptionOfferDetails
            ?.firstOrNull()
            ?.offerToken ?: return

        val params = BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(
                listOf(
                    BillingFlowParams.ProductDetailsParams.newBuilder()
                        .setProductDetails(product)
                        .setOfferToken(offerToken)
                        .build()
                )
            ).build()

        billingClient.launchBillingFlow(activity, params)
    }

    private fun startConnection() {
        if (billingClient.isReady) {
            scope.launch { refresh() }
            return
        }

        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(result: BillingResult) {
                if (result.responseCode == BillingClient.BillingResponseCode.OK) {
                    scope.launch { refresh() }
                } else {
                    scope.launch {
                        val isPremium = preferencesRepository.premiumEnabled.first()
                        _entitlement.value = PremiumEntitlement(
                            isPremium = isPremium,
                            source = EntitlementSource.LOCAL_CACHE,
                            message = "Billing unavailable. Showing cached premium status."
                        )
                    }
                }
            }

            override fun onBillingServiceDisconnected() = Unit
        })
    }

    private suspend fun queryProductCatalog() {
        val products = listOf(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(BillingProductIds.PREMIUM_MONTHLY)
                .setProductType(BillingClient.ProductType.SUBS)
                .build(),
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(BillingProductIds.PREMIUM_YEARLY)
                .setProductType(BillingClient.ProductType.SUBS)
                .build()
        )

        val result = billingClient.queryProductDetails(
            QueryProductDetailsParams.newBuilder().setProductList(products).build()
        )

        val details = result.productDetailsList.orEmpty()
        _catalog.value = BillingCatalog(
            monthly = details.find { it.productId == BillingProductIds.PREMIUM_MONTHLY },
            yearly = details.find { it.productId == BillingProductIds.PREMIUM_YEARLY }
        )
    }

    private suspend fun refreshEntitlementFromPlay() {
        val purchases = mutableListOf<Purchase>()

        val subs = billingClient.queryPurchasesAsync(
            QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.SUBS).build()
        )
        purchases += subs.purchasesList

        val active = purchases.any { purchase ->
            val hasPremiumProduct = purchase.products.any {
                it == BillingProductIds.PREMIUM_MONTHLY || it == BillingProductIds.PREMIUM_YEARLY
            }
            val purchased = purchase.purchaseState == Purchase.PurchaseState.PURCHASED
            hasPremiumProduct && purchased
        }

        purchases.filter { !it.isAcknowledged }.forEach(::acknowledgePurchase)

        preferencesRepository.setPremiumEnabled(active)
        _entitlement.value = PremiumEntitlement(active, if (active) EntitlementSource.PLAY_BILLING else EntitlementSource.NONE)
    }

    private fun acknowledgePurchase(purchase: Purchase) {
        val params = AcknowledgePurchaseParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()
        billingClient.acknowledgePurchase(params) {}
    }
}

object BillingProductIds {
    // Configure these IDs in Play Console and keep synced with this file.
    const val PREMIUM_MONTHLY = "smart_scanner_premium_monthly"
    const val PREMIUM_YEARLY = "smart_scanner_premium_yearly"
}
