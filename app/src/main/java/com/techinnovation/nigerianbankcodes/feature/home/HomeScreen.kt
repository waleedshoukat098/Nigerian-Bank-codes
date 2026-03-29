package com.techinnovation.nigerianbankcodes.feature.home

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Scanner
import androidx.compose.material.icons.filled.TextFormat
import androidx.compose.material.icons.filled.Transform
import androidx.compose.material.icons.outlined.Scanner
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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
import com.techinnovation.nigerianbankcodes.ui.theme.BrandAmber
import com.techinnovation.nigerianbankcodes.ui.theme.BrandEmerald
import com.techinnovation.nigerianbankcodes.ui.theme.BrandGhost
import com.techinnovation.nigerianbankcodes.ui.theme.BrandIndigo
import com.techinnovation.nigerianbankcodes.ui.theme.BrandInk
import com.techinnovation.nigerianbankcodes.ui.theme.BrandSurface
import com.techinnovation.nigerianbankcodes.ui.theme.BrandWhite
import com.techinnovation.nigerianbankcodes.ui.theme.TextGray

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
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            val navBarPadding = WindowInsets.navigationBars.asPaddingValues()
            NavigationBar(
                containerColor = BrandWhite,
                tonalElevation = 0.dp,
                modifier = Modifier.height(80.dp + navBarPadding.calculateBottomPadding())
            ) {
                val items = listOf(
                    Triple("Home", Icons.Default.Home, 0),
                    Triple("Scan", Icons.Default.Scanner, 1),
                    Triple("Convert", Icons.Default.Transform, 2),
                    Triple("History", Icons.Default.History, 3)
                )
                items.forEach { (label, icon, index) ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { 
                            selectedTab = index
                            when(index) {
                                1 -> onOpenScanner()
                                2 -> onOpenConverter()
                                3 -> onOpenHistory()
                            }
                        },
                        icon = { Icon(icon, contentDescription = null) },
                        label = { Text(label, fontSize = 12.sp, fontWeight = FontWeight.Normal) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = BrandIndigo,
                            selectedTextColor = BrandIndigo,
                            unselectedIconColor = TextGray,
                            unselectedTextColor = TextGray,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        },
        containerColor = BrandSurface
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Good morning",
                        color = TextGray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "Smart Utility",
                        color = BrandInk,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium // 500 as per H1
                    )
                }
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(if (state.isPremium) BrandIndigo else BrandGhost)
                        .clickable(onClick = onOpenPremium),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, contentDescription = null, tint = if (state.isPremium) BrandWhite else BrandIndigo, modifier = Modifier.size(20.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp)) // 24px section gap

            // Main Scan Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(16.dp)) // Large card radius
                    .background(BrandIndigo)
                    .clickable(onClick = onOpenScanner)
                    .padding(24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(BrandWhite.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Scanner,
                            contentDescription = null,
                            tint = BrandWhite,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Scan document",
                            color = BrandWhite,
                            fontSize = 18.sp, // Section header H2 size
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Tap to open camera",
                            color = BrandWhite.copy(alpha = 0.7f),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = null,
                        tint = BrandWhite,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "QUICK TOOLS",
                color = TextGray,
                fontSize = 12.sp, // Caption/muted size
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.5.sp
            )

            Spacer(modifier = Modifier.height(12.dp)) // Default gap

            // Quick Tools Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                item {
                    ToolCard(
                        title = "QR scanner",
                        subtitle = "Scan any code",
                        icon = Icons.Default.QrCodeScanner,
                        iconColor = BrandIndigo,
                        bgColor = BrandGhost,
                        onClick = onOpenScanner
                    )
                }
                item {
                    ToolCard(
                        title = "OCR text",
                        subtitle = "Extract text",
                        icon = Icons.Default.TextFormat,
                        iconColor = BrandEmerald,
                        bgColor = BrandEmerald.copy(alpha = 0.1f),
                        onClick = onOpenScanner
                    )
                }
                item {
                    ToolCard(
                        title = "Unit convert",
                        subtitle = "15 categories",
                        icon = Icons.Default.Transform,
                        iconColor = BrandAmber,
                        bgColor = BrandAmber.copy(alpha = 0.1f),
                        onClick = onOpenConverter
                    )
                }
                item {
                    ToolCard(
                        title = "Currency",
                        subtitle = "160+ currencies",
                        icon = Icons.Default.CurrencyExchange,
                        iconColor = BrandIndigo,
                        bgColor = BrandGhost,
                        onClick = onOpenConverter
                    )
                }
            }

            if (state.recentItems.isNotEmpty()) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "RECENT",
                    color = TextGray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                state.recentItems.forEach { item ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(BrandWhite)
                            .padding(14.dp)
                    ) {
                        Text(item.title, color = BrandInk, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                        Text(item.preview, color = TextGray, fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun ToolCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    iconColor: Color,
    bgColor: Color,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)) // Cards radius
            .background(BrandWhite)
            .clickable(onClick = onClick)
            .padding(16.dp) // Card padding
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp)) // Inner radius
                .background(bgColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = title,
            color = BrandInk,
            fontSize = 15.sp, // Card title size
            fontWeight = FontWeight.Medium
        )
        Text(
            text = subtitle,
            color = TextGray,
            fontSize = 12.sp, // Caption size
            fontWeight = FontWeight.Normal
        )
    }
}
