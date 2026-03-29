package com.techinnovation.nigerianbankcodes.feature.converter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.Compress
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.ViewStream
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techinnovation.nigerianbankcodes.ui.theme.BrandGhost
import com.techinnovation.nigerianbankcodes.ui.theme.BrandIndigo
import com.techinnovation.nigerianbankcodes.ui.theme.BrandInk
import com.techinnovation.nigerianbankcodes.ui.theme.BrandSurface
import com.techinnovation.nigerianbankcodes.ui.theme.BrandWhite
import com.techinnovation.nigerianbankcodes.ui.theme.TextGray

@Composable
fun ConverterScreen(viewModel: ConverterViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var selectedFilter by remember { mutableStateOf("All") }
    var showToolDetail by remember { mutableStateOf<QuickToolItem?>(null) }

    val statusBarPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val navBarPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    Scaffold(
        containerColor = BrandSurface
    ) { padding ->
        if (showToolDetail == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(top = statusBarPadding)
                    .padding(bottom = navBarPadding)
                    .padding(horizontal = 24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Quick tools",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrandInk
                    )
                    IconButton(onClick = { /* More options */ }) {
                        Icon(Icons.Default.MoreHoriz, contentDescription = null, tint = BrandInk)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Filters
                val filters = listOf("All", "PDF", "Image", "Text")
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(filters) { filter ->
                        val isSelected = selectedFilter == filter
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(if (isSelected) BrandIndigo else BrandGhost)
                                .clickable { selectedFilter = filter }
                                .padding(horizontal = 20.dp, vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = filter,
                                color = if (isSelected) BrandWhite else TextGray,
                                fontSize = 14.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Tools Grid
                val tools = listOf(
                    QuickToolItem("Currency", "Live exchange rates", Icons.Default.ViewStream, BrandIndigo, ConverterTool.Currency),
                    QuickToolItem("QR generator", "Create any QR code", Icons.Default.QrCode, BrandIndigo, ConverterTool.QR_GEN),
                    QuickToolItem("PDF merge", "Combine multiple PDFs", Icons.Default.PictureAsPdf, BrandIndigo, ConverterTool.Unit),
                    QuickToolItem("Image compress", "Reduce file size", Icons.Default.Compress, Color(0xFFF59E0B), ConverterTool.Storage),
                    QuickToolItem("Text case", "Change text style", Icons.Default.Abc, Color(0xFFEF4444), ConverterTool.Percentage),
                    QuickToolItem("Word count", "Count words, chars", Icons.Default.ViewStream, BrandInk, ConverterTool.Age)
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(tools.size) { index ->
                        val tool = tools[index]
                        ToolGridCard(tool) {
                            showToolDetail = tool
                            viewModel.selectTool(tool.toolType)
                        }
                    }
                }
            }
        } else {
            // Tool Detail / Calculation Screen
            ToolDetailContent(
                tool = showToolDetail!!,
                state = state,
                viewModel = viewModel,
                onBack = { showToolDetail = null },
                statusBarPadding = statusBarPadding
            )
        }
    }
}

@Composable
fun ToolDetailContent(
    tool: QuickToolItem,
    state: ConverterUiState,
    viewModel: ConverterViewModel,
    onBack: () -> Unit,
    statusBarPadding: androidx.compose.ui.unit.Dp
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = statusBarPadding)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable(onClick = onBack).padding(vertical = 16.dp)
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBackIos, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = tool.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp)
        ) {
            Column(
                modifier = Modifier.background(BrandWhite).padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = state.inputA,
                    onValueChange = viewModel::updateInputA,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(if (tool.toolType == ConverterTool.Currency) "USD Amount" else "Input Text") },
                    shape = RoundedCornerShape(12.dp)
                )
                
                if (tool.toolType == ConverterTool.Percentage) {
                    OutlinedTextField(
                        value = state.inputB,
                        onValueChange = viewModel::updateInputB,
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Base Value") },
                        shape = RoundedCornerShape(12.dp)
                    )
                }

                Button(
                    onClick = viewModel::calculate,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BrandIndigo)
                ) {
                    Text(if (tool.toolType == ConverterTool.QR_GEN) "Generate" else "Calculate")
                }

                if (state.error != null) {
                    Text(state.error, color = Color.Red, fontSize = 14.sp)
                }

                if (state.result.isNotBlank()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Result", color = TextGray, fontSize = 14.sp)
                    Text(
                        text = state.result,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrandInk
                    )
                    Button(
                        onClick = viewModel::saveResult,
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = BrandGhost)
                    ) {
                        Text("Save Result", color = BrandInk)
                    }
                }
            }
        }
    }
}

data class QuickToolItem(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val color: Color,
    val toolType: ConverterTool
)

@Composable
fun ToolGridCard(tool: QuickToolItem, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(BrandWhite)
            .clickable(onClick = onClick)
            .padding(20.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(tool.color.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(tool.icon, contentDescription = null, tint = tool.color, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = tool.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = BrandInk
        )
        Text(
            text = tool.subtitle,
            fontSize = 12.sp,
            color = TextGray,
            lineHeight = 16.sp
        )
    }
}
