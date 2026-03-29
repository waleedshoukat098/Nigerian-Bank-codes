package com.techinnovation.nigerianbankcodes.core.navigation

sealed interface AppDestination {
    val route: String

    data object Splash : AppDestination { override val route = "splash" }
    data object Onboarding : AppDestination { override val route = "onboarding" }
    data object Home : AppDestination { override val route = "home" }
    data object Scanner : AppDestination { override val route = "scanner" }
    data object Converter : AppDestination { override val route = "converter" }
    data object History : AppDestination { override val route = "history" }
    data object Premium : AppDestination { override val route = "premium" }
    data object Settings : AppDestination { override val route = "settings" }
}
