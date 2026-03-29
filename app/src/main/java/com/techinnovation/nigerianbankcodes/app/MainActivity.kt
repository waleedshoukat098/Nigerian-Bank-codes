package com.techinnovation.nigerianbankcodes.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techinnovation.nigerianbankcodes.core.navigation.AppNavGraph
import com.techinnovation.nigerianbankcodes.ui.theme.SmartScannerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appViewModel: MainViewModel = hiltViewModel()
            val darkMode by appViewModel.darkMode.collectAsStateWithLifecycle()
            val onboardingCompleted by appViewModel.onboardingCompleted.collectAsStateWithLifecycle()
            SmartScannerTheme(darkTheme = darkMode) {
                AppNavGraph(
                    onboardingCompleted = onboardingCompleted,
                    onOnboardingCompleted = appViewModel::completeOnboarding
                )
            }
        }
    }
}
