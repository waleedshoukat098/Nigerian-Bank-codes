package com.techinnovation.nigerianbankcodes.feature.premium

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techinnovation.nigerianbankcodes.core.billing.EntitlementSource
import com.techinnovation.nigerianbankcodes.core.designsystem.components.SectionHeader

@Composable
fun PremiumScreen(viewModel: PremiumViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackState = remember { SnackbarHostState() }
    val activity = LocalContext.current as? Activity

    LaunchedEffect(Unit) {
        viewModel.events.collect { snackState.showSnackbar(it) }
    }

    Scaffold(snackbarHost = { SnackbarHost(snackState) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SectionHeader(if (state.entitlement.isPremium) "Premium Active" else "Upgrade to Premium")
            Text(
                when {
                    state.entitlement.isPremium -> "Premium unlocked on this account."
                    state.entitlement.source == EntitlementSource.LOCAL_CACHE -> state.entitlement.message ?: "Billing temporarily unavailable."
                    else -> "Unlock unlimited scans, converter pro tools, and priority OCR."
                }
            )
            FeatureComparisonCard()
            PlanCard(
                "Monthly",
                state.monthlyPrice ?: "$3.99",
                "Flexible monthly plan",
                onCta = { activity?.let(viewModel::purchaseMonthly) }
            )
            PlanCard(
                "Yearly",
                state.yearlyPrice ?: "$24.99",
                "Best value for power users",
                onCta = { activity?.let(viewModel::purchaseYearly) }
            )
            OutlinedButton(onClick = viewModel::restore, modifier = Modifier.fillMaxWidth()) { Text("Restore purchases") }
            Text("Note: Add real product IDs and Play Console configuration before production release.")
        }
    }
}

@Composable
private fun FeatureComparisonCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("Free vs Premium")
            Text("• Free: daily scan limits + basic tools")
            Text("• Premium: unlimited scans, advanced converters, future cloud sync")
        }
    }
}

@Composable
private fun PlanCard(title: String, price: String, subtitle: String, onCta: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(title)
            Text(price)
            Text(subtitle)
            Button(onClick = onCta) { Text("Choose Plan") }
        }
    }
}
