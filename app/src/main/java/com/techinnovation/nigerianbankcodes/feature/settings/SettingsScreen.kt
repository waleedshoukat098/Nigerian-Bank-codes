package com.techinnovation.nigerianbankcodes.feature.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.weight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techinnovation.nigerianbankcodes.core.designsystem.components.SectionHeader

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SectionHeader("Settings")
        ToggleCard("Dark mode", "Adjust app appearance", state.darkMode, viewModel::setDarkMode)
        ToggleCard("Auto-save results", "Automatically persist OCR and conversions", state.autoSave, viewModel::setAutoSave)
        ToggleCard("Scan vibration", "Haptic feedback after successful scan", state.vibration, viewModel::setVibration)

        Card {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("Account", style = MaterialTheme.typography.titleMedium)
                Text(if (state.premiumEnabled) "Premium plan active" else "Using free plan")
            }
        }

        Text("Privacy", style = MaterialTheme.typography.titleMedium)
        Text("All scans remain on-device unless you share/export them.")
        Text("Version 1.0.0", style = MaterialTheme.typography.labelMedium)
    }
}

@Composable
private fun ToggleCard(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card {
        Row(modifier = Modifier.padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(modifier = Modifier.weight(1f)) {
                Text(title)
                Text(subtitle, style = MaterialTheme.typography.labelMedium)
            }
            Switch(checked = checked, onCheckedChange = onCheckedChange)
        }
    }
}
