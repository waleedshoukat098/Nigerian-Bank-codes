package com.techinnovation.nigerianbankcodes.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.techinnovation.nigerianbankcodes.feature.converter.ConverterScreen
import com.techinnovation.nigerianbankcodes.feature.history.HistoryScreen
import com.techinnovation.nigerianbankcodes.feature.home.HomeScreen
import com.techinnovation.nigerianbankcodes.feature.onboarding.OnboardingScreen
import com.techinnovation.nigerianbankcodes.feature.onboarding.SplashScreen
import com.techinnovation.nigerianbankcodes.feature.premium.PremiumScreen
import com.techinnovation.nigerianbankcodes.feature.scanner.ScannerScreen
import com.techinnovation.nigerianbankcodes.feature.settings.SettingsScreen

@Composable
fun AppNavGraph(
    onboardingCompleted: Boolean,
    onOnboardingCompleted: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppDestination.Splash.route) {
        composable(AppDestination.Splash.route) {
            SplashScreen(onSplashFinished = {
                val destination = if (onboardingCompleted) AppDestination.Home.route else AppDestination.Onboarding.route
                navController.navigate(destination) {
                    popUpTo(AppDestination.Splash.route) { inclusive = true }
                }
            })
        }
        composable(AppDestination.Onboarding.route) {
            OnboardingScreen(onContinue = {
                onOnboardingCompleted()
                navController.navigate(AppDestination.Home.route) {
                    popUpTo(AppDestination.Onboarding.route) { inclusive = true }
                }
            })
        }
        composable(AppDestination.Home.route) {
            HomeScreen(
                onOpenScanner = { navController.navigate(AppDestination.Scanner.route) },
                onOpenConverter = { navController.navigate(AppDestination.Converter.route) },
                onOpenHistory = { navController.navigate(AppDestination.History.route) },
                onOpenPremium = { navController.navigate(AppDestination.Premium.route) },
                onOpenSettings = { navController.navigate(AppDestination.Settings.route) }
            )
        }
        composable(AppDestination.Scanner.route) { ScannerScreen() }
        composable(AppDestination.Converter.route) { ConverterScreen() }
        composable(AppDestination.History.route) { HistoryScreen() }
        composable(AppDestination.Premium.route) { PremiumScreen() }
        composable(AppDestination.Settings.route) { SettingsScreen() }
    }
}
