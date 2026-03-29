package com.techinnovation.nigerianbankcodes.core.billing

import com.android.billingclient.api.ProductDetails

data class PremiumEntitlement(
    val isPremium: Boolean,
    val source: EntitlementSource,
    val message: String? = null
)

enum class EntitlementSource {
    PLAY_BILLING,
    LOCAL_CACHE,
    NONE
}

data class BillingCatalog(
    val monthly: ProductDetails? = null,
    val yearly: ProductDetails? = null
)
