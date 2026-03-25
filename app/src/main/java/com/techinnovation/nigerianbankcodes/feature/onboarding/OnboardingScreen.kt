package com.techinnovation.nigerianbankcodes.feature.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingScreen(onContinue: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text("SMART SCANNER", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Scan QR codes, extract text from images, and run quick conversions in one calm workspace.")
        Text("• Local-first processing for privacy")
        Text("• Searchable history for everything")
        Text("• Fast utility tools in a single app")
        Button(onClick = onContinue, modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
            Text("Get started")
        }
    }
}
