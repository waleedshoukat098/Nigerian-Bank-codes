package com.techinnovation.nigerianbankcodes.core.billing

import android.app.Activity
import kotlinx.coroutines.flow.Flow

interface PremiumManager {
    val entitlement: Flow<PremiumEntitlement>
    val catalog: Flow<BillingCatalog>
    suspend fun refresh()
    suspend fun restorePurchases()
    fun launchPurchase(activity: Activity, plan: PremiumPlan)
}

enum class PremiumPlan {
    MONTHLY,
    YEARLY
}
