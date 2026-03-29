package com.techinnovation.nigerianbankcodes.feature.scanner

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techinnovation.nigerianbankcodes.ui.theme.BrandGhost
import com.techinnovation.nigerianbankcodes.ui.theme.BrandIndigo
import com.techinnovation.nigerianbankcodes.ui.theme.BrandInk
import com.techinnovation.nigerianbankcodes.ui.theme.BrandSurface
import com.techinnovation.nigerianbankcodes.ui.theme.BrandWhite
import com.techinnovation.nigerianbankcodes.ui.theme.TextGray

@Composable
fun OcrResultScreen(
    text: String,
    onBack: () -> Unit
) {
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val navBarPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val clipboardManager = LocalClipboardManager.current

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
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable(onClick = onBack)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBackIos, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Extracted text", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Text(
                    text = "Share",
                    color = BrandIndigo,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable { /* Handle Share */ }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Text Content Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(24.dp))
                    .background(BrandWhite)
                    .padding(24.dp)
            ) {
                Text(
                    text = text,
                    fontSize = 15.sp,
                    color = BrandInk,
                    lineHeight = 22.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Meta info
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    val wordCount = text.split("\\s+".toRegex()).filter { it.isNotBlank() }.size
                    Text(text = "$wordCount words detected", color = TextGray, fontSize = 13.sp)
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(BrandIndigo.copy(alpha = 0.1f))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(text = "99% accuracy", color = BrandIndigo, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { clipboardManager.setText(AnnotatedString(text)) },
                    modifier = Modifier.weight(1f).height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BrandIndigo)
                ) {
                    Text("Copy all")
                }
                Button(
                    onClick = { /* Save as .txt */ },
                    modifier = Modifier.weight(1f).height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BrandGhost),
                ) {
                    Text("Save as .txt", color = BrandInk)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Share text */ },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BrandGhost)
            ) {
                Text("Share text", color = BrandInk)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Original Image Placeholder
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(BrandWhite)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Original image", color = TextGray, fontSize = 14.sp)
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(BrandGhost)
                )
            }
        }
    }
}
