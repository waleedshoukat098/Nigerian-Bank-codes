package com.techinnovation.nigerianbankcodes.feature.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
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
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val statusBarPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val navBarPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    Scaffold(
        containerColor = BrandSurface
    ) { padding ->
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
                    text = "Settings",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandInk
                )
                IconButton(onClick = { /* More options */ }) {
                    Icon(Icons.Default.MoreHoriz, contentDescription = null, tint = BrandInk)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Settings List Container
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(BrandWhite)
                    .padding(vertical = 8.dp)
            ) {
                SettingsItem(
                    icon = Icons.Default.Star,
                    iconColor = BrandIndigo,
                    title = "Account",
                    trailing = {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(BrandInk)
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text("Pro", color = BrandWhite, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    },
                    onClick = {}
                )
                SettingsItem(
                    icon = Icons.Default.PictureAsPdf,
                    iconColor = Color(0xFF10B981),
                    title = "Dark mode",
                    trailing = { Switch(checked = state.darkMode, onCheckedChange = viewModel::setDarkMode) },
                    onClick = {}
                )
                SettingsItem(
                    icon = Icons.Default.History,
                    iconColor = Color(0xFFF59E0B),
                    title = "Auto-save scans",
                    trailing = { Switch(checked = state.autoSave, onCheckedChange = viewModel::setAutoSave) },
                    onClick = {}
                )
                SettingsItem(
                    icon = Icons.Default.Person,
                    iconColor = Color(0xFFEF4444),
                    title = "Haptic feedback",
                    trailing = { Switch(checked = state.vibration, onCheckedChange = viewModel::setVibration) },
                    onClick = {}
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Other options
            SettingsSimpleItem(
                title = "Rate the app",
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${context.packageName}"))
                    context.startActivity(intent)
                }
            )
            SettingsSimpleItem(
                title = "Privacy policy",
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://example.com/privacy"))
                    context.startActivity(intent)
                }
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            Text(
                text = "App version 1.0.0",
                color = TextGray,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
fun SettingsItem(
    icon: ImageVector,
    iconColor: Color,
    title: String,
    value: String? = null,
    trailing: @Composable (() -> Unit)? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(iconColor.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title, fontSize = 16.sp, color = BrandInk, modifier = Modifier.weight(1f))
        
        if (value != null) {
            Text(text = value, fontSize = 14.sp, color = TextGray)
            Spacer(modifier = Modifier.width(8.dp))
        }
        
        if (trailing != null) {
            trailing()
            Spacer(modifier = Modifier.width(8.dp))
        }
        
        Icon(Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = null, tint = TextGray.copy(alpha = 0.5f), modifier = Modifier.size(12.dp))
    }
}

@Composable
fun SettingsSimpleItem(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, fontSize = 16.sp, color = BrandInk)
        Icon(Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = null, tint = TextGray.copy(alpha = 0.5f), modifier = Modifier.size(12.dp))
    }
}
