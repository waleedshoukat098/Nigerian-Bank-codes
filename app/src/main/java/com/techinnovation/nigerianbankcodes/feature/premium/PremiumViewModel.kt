package com.techinnovation.nigerianbankcodes.feature.premium

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techinnovation.nigerianbankcodes.core.billing.PremiumEntitlement
import com.techinnovation.nigerianbankcodes.core.billing.PremiumManager
import com.techinnovation.nigerianbankcodes.core.billing.PremiumPlan
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PremiumViewModel @Inject constructor(
    private val premiumManager: PremiumManager
) : ViewModel() {

    private val _events = MutableSharedFlow<String>()
    val events = _events.asSharedFlow()

    val state: StateFlow<PremiumUiState> = combine(
        premiumManager.entitlement,
        premiumManager.catalog
    ) { entitlement, catalog ->
        PremiumUiState(
            entitlement = entitlement,
            monthlyPrice = catalog.monthly?.subscriptionOfferDetails?.firstOrNull()?.pricingPhases?.pricingPhaseList?.firstOrNull()?.formattedPrice,
            yearlyPrice = catalog.yearly?.subscriptionOfferDetails?.firstOrNull()?.pricingPhases?.pricingPhaseList?.firstOrNull()?.formattedPrice
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), PremiumUiState())

    init {
        viewModelScope.launch { premiumManager.refresh() }
    }

    fun purchaseMonthly(activity: Activity) {
        premiumManager.launchPurchase(activity, PremiumPlan.MONTHLY)
    }

    fun purchaseYearly(activity: Activity) {
        premiumManager.launchPurchase(activity, PremiumPlan.YEARLY)
    }

    fun restore() {
        viewModelScope.launch {
            premiumManager.restorePurchases()
            _events.emit("Restore completed")
        }
    }
}

data class PremiumUiState(
    val entitlement: PremiumEntitlement = PremiumEntitlement(false, com.techinnovation.nigerianbankcodes.core.billing.EntitlementSource.NONE),
    val monthlyPrice: String? = null,
    val yearlyPrice: String? = null
)
