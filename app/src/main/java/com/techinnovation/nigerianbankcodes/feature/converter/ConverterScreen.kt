package com.techinnovation.nigerianbankcodes.feature.converter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techinnovation.nigerianbankcodes.core.billing.PremiumGate
import com.techinnovation.nigerianbankcodes.core.designsystem.components.SectionHeader

@Composable
fun ConverterScreen(viewModel: ConverterViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackState = remember { SnackbarHostState() }

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
            SectionHeader("Converter Suite", actionText = if (state.isPremium) "Premium" else "Free")
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(ConverterTool.entries) { tool ->
                    FilterChip(
                        selected = tool == state.selectedTool,
                        onClick = { viewModel.selectTool(tool) },
                        label = {
                            val isLocked = PremiumGate.isToolLocked(tool.name, state.isPremium)
                            Text(if (isLocked) "${tool.name} 🔒" else tool.name)
                        }
                    )
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = state.inputA,
                        onValueChange = viewModel::updateInputA,
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(primaryLabel(state.selectedTool)) }
                    )
                    if (requiresSecondInput(state.selectedTool)) {
                        OutlinedTextField(
                            value = state.inputB,
                            onValueChange = viewModel::updateInputB,
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(secondaryLabel(state.selectedTool)) }
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(onClick = viewModel::calculate) { Text("Convert") }
                        Button(onClick = viewModel::saveResult, enabled = state.result.isNotBlank()) { Text("Save") }
                    }
                    state.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                    if (state.result.isNotBlank()) {
                        Text("Result", style = MaterialTheme.typography.titleSmall)
                        Text(state.result, style = MaterialTheme.typography.headlineSmall)
                    }
                }
            }
        }
    }
}

private fun requiresSecondInput(tool: ConverterTool): Boolean = tool == ConverterTool.Percentage

private fun primaryLabel(tool: ConverterTool): String = when (tool) {
    ConverterTool.Unit -> "Meters"
    ConverterTool.Currency -> "USD"
    ConverterTool.Percentage -> "Percentage (%)"
    ConverterTool.Age -> "Birth Year"
    ConverterTool.Storage -> "Megabytes"
}

private fun secondaryLabel(tool: ConverterTool): String = when (tool) {
    ConverterTool.Percentage -> "Base Value"
    else -> ""
}
