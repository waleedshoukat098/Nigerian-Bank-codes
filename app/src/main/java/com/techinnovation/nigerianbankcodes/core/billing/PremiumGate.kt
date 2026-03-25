package com.techinnovation.nigerianbankcodes.core.billing

object PremiumGate {
    fun isToolLocked(toolName: String, isPremium: Boolean): Boolean {
        if (isPremium) return false
        return toolName in setOf("Age", "Storage")
    }
}
