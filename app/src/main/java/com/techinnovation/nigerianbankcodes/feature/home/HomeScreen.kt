package com.techinnovation.nigerianbankcodes.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techinnovation.nigerianbankcodes.core.designsystem.components.EmptyStateCard
import com.techinnovation.nigerianbankcodes.core.designsystem.components.SectionHeader
import com.techinnovation.nigerianbankcodes.core.designsystem.components.SmartCard

@Composable
fun HomeScreen(
    onOpenScanner: () -> Unit,
    onOpenConverter: () -> Unit,
    onOpenHistory: () -> Unit,
    onOpenPremium: () -> Unit,
    onOpenSettings: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("Smart Scanner", style = MaterialTheme.typography.headlineMedium)
            Text("Fast tools for scanning, OCR, conversion and history.")
        }

        item {
            if (!state.isPremium) {
                SmartCard("Upgrade to Premium", "Unlock unlimited scans and advanced converters", onOpenPremium)
            }
        }

        item { SectionHeader("Quick Actions") }
        item { SmartCard("QR / Barcode Scanner", "Scan and act on links or product codes", onOpenScanner) }
        item { SmartCard("Document OCR", "Extract editable text from documents", onOpenScanner) }
        item { SmartCard("Converter Suite", "Units, currency, percentages, age, storage", onOpenConverter) }

        item { SectionHeader("Recent Activity", actionText = "View all") }
        if (state.recentItems.isEmpty()) {
            item {
                EmptyStateCard("No history yet", "Your recent scans and conversions will appear here.")
            }
        } else {
            items(state.recentItems) { item ->
                SmartCard(item.title, item.preview, onOpenHistory)
            }
        }

        item { SectionHeader("More") }
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                SmartCard("Settings", "Privacy, preferences, and account controls", onOpenSettings)
            }
        }
    }
}
