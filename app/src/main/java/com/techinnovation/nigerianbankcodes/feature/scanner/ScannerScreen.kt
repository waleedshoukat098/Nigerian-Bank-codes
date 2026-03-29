package com.techinnovation.nigerianbankcodes.feature.scanner

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CropFree
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.techinnovation.nigerianbankcodes.core.designsystem.components.PermissionPromptCard
import com.techinnovation.nigerianbankcodes.ui.theme.BrandIndigo
import com.techinnovation.nigerianbankcodes.ui.theme.BrandInk

@Composable
fun ScannerScreen(
    viewModel: ScannerViewModel = hiltViewModel(),
    onNavigateToHistory: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var hasCameraPermission by remember { mutableStateOf(false) }
    val snackState = remember { SnackbarHostState() }
    val clipboardManager = LocalClipboardManager.current

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasCameraPermission = granted
        if (!granted) viewModel.emitInfo("Camera permission denied")
    }

    LaunchedEffect(Unit) { permissionLauncher.launch(Manifest.permission.CAMERA) }

    LaunchedEffect(Unit) {
        viewModel.events.collect { snackState.showSnackbar(it) }
    }

    val statusBarPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val navBarPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackState) },
        containerColor = BrandInk
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Camera Preview
            if (hasCameraPermission) {
                ScannerCameraPreview(
                    modifier = Modifier.fillMaxSize(),
                    onResult = viewModel::onQrResultDetected
                )
            } else {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    PermissionPromptCard(
                        title = "Camera Access Needed",
                        description = "We only use camera frames locally to read QR and barcodes.",
                        ctaText = "Grant Permission",
                        onCtaClick = { permissionLauncher.launch(Manifest.permission.CAMERA) }
                    )
                }
            }

            // Top Bar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp + statusBarPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(32.dp))
                        .background(Color.Black.copy(alpha = 0.5f))
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val tabs = listOf("Doc", "QR", "Text")
                    tabs.forEachIndexed { index, title ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(24.dp))
                                .background(if (state.selectedTab == index) Color.White.copy(alpha = 0.2f) else Color.Transparent)
                                .clickable { viewModel.setSelectedTab(index) }
                                .padding(horizontal = 20.dp, vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = title,
                                color = if (state.selectedTab == index) Color.White else Color.White.copy(alpha = 0.5f),
                                fontSize = 14.sp,
                                fontWeight = if (state.selectedTab == index) FontWeight.Bold else FontWeight.Medium
                            )
                        }
                    }
                    IconButton(onClick = { /* Menu */ }) {
                        Icon(Icons.Default.Menu, contentDescription = null, tint = Color.White)
                    }
                }
            }

            // Central Frame Overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 120.dp + navBarPadding),
                contentAlignment = Alignment.Center
            ) {
                when (state.selectedTab) {
                    0 -> DocumentOverlay()
                    1 -> QrOverlay()
                    2 -> TextOverlay()
                }
            }

            // Bottom UI
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 40.dp + navBarPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // QR Result Card
                if (state.selectedTab == 1 && state.qrResult != null) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color.White.copy(alpha = 0.1f))
                            .padding(20.dp)
                    ) {
                        Column {
                            Text(
                                text = "Last scanned",
                                color = Color.White.copy(alpha = 0.5f),
                                fontSize = 12.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = state.qrResult ?: "",
                                color = Color.White,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                maxLines = 2
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(BrandIndigo.copy(alpha = 0.3f))
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Text("URL", color = BrandIndigo, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                    Text(
                                        text = "Open",
                                        color = Color.White.copy(alpha = 0.6f),
                                        fontSize = 13.sp,
                                        modifier = Modifier.clickable { /* Handle Open */ }
                                    )
                                    Text(
                                        text = "Copy",
                                        color = Color.White.copy(alpha = 0.6f),
                                        fontSize = 13.sp,
                                        modifier = Modifier.clickable { 
                                            clipboardManager.setText(AnnotatedString(state.qrResult ?: ""))
                                            viewModel.emitInfo("Copied to clipboard")
                                        }
                                    )
                                    Text(
                                        text = "Share",
                                        color = Color.White.copy(alpha = 0.6f),
                                        fontSize = 13.sp,
                                        modifier = Modifier.clickable { /* Handle Share */ }
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }

                // Shutter Controls
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val sideIcon = if (state.selectedTab == 0) Icons.Default.CropFree else Icons.Default.Add
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.1f))
                            .clickable { /* Extra action */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(sideIcon, contentDescription = null, tint = Color.White, modifier = Modifier.size(24.dp))
                    }

                    Spacer(modifier = Modifier.width(32.dp))

                    // Main Shutter Button
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .border(4.dp, BrandIndigo, CircleShape)
                            .padding(8.dp)
                            .clip(CircleShape)
                            .background(BrandIndigo)
                            .clickable { viewModel.saveCurrentResult() },
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color.White)
                        )
                    }

                    Spacer(modifier = Modifier.width(32.dp))

                    val rightIcon = if (state.selectedTab == 0) Icons.Default.Add else Icons.Default.History
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.1f))
                            .clickable { 
                                if (state.selectedTab != 0) onNavigateToHistory()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(rightIcon, contentDescription = null, tint = Color.White, modifier = Modifier.size(24.dp))
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                val statusText = when (state.selectedTab) {
                    0 -> "Auto-edge detection active"
                    1 -> "Scanner ready"
                    else -> "Extracting text..."
                }
                Text(statusText, color = Color.White.copy(alpha = 0.5f), fontSize = 13.sp)
            }
        }
    }
}

@Composable
private fun DocumentOverlay() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(width = 300.dp, height = 400.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val stroke = 3.dp.toPx()
                val cornerLength = 40.dp.toPx()
                val path = Path().apply {
                    // Top Left
                    moveTo(0f, cornerLength)
                    lineTo(0f, 0f)
                    lineTo(cornerLength, 0f)
                    
                    // Top Right
                    moveTo(size.width - cornerLength, 0f)
                    lineTo(size.width, 0f)
                    lineTo(size.width, cornerLength)
                    
                    // Bottom Right
                    moveTo(size.width, size.height - cornerLength)
                    lineTo(size.width, size.height)
                    lineTo(size.width - cornerLength, size.height)
                    
                    // Bottom Left
                    moveTo(cornerLength, size.height)
                    lineTo(0f, size.height)
                    lineTo(0f, size.height - cornerLength)
                }
                drawPath(path, color = BrandIndigo, style = Stroke(stroke))
                
                // Horizontal Line
                drawLine(
                    color = BrandIndigo.copy(alpha = 0.5f),
                    start = androidx.compose.ui.geometry.Offset(0f, size.height * 0.45f),
                    end = androidx.compose.ui.geometry.Offset(size.width, size.height * 0.45f),
                    strokeWidth = 2.dp.toPx()
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Position document within frame",
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun QrOverlay() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(260.dp),
            contentAlignment = Alignment.Center
        ) {
            // Outer Frame
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(2.dp, BrandIndigo.copy(alpha = 0.5f), RoundedCornerShape(32.dp))
            )
            // Inner Box
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .border(3.dp, BrandIndigo, RoundedCornerShape(20.dp))
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Aim at QR or barcode",
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun TextOverlay() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(150.dp)
                .border(2.dp, BrandIndigo, RoundedCornerShape(12.dp))
                .background(BrandIndigo.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Text("TEXT SCANNER", color = Color.White.copy(alpha = 0.3f), fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Focus on printed text",
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun ScannerCameraPreview(
    modifier: Modifier = Modifier,
    onResult: (String) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val previewView = remember { PreviewView(context) }
    val scanner = remember { BarcodeScanning.getClient() }

    DisposableEffect(Unit) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        val executor = ContextCompat.getMainExecutor(context)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also { it.surfaceProvider = previewView.surfaceProvider }
            val analysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also { it.setAnalyzer(executor, BarcodeAnalyzer(scanner, onResult)) }
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(lifecycleOwner, CameraSelector.DEFAULT_BACK_CAMERA, preview, analysis)
        }, executor)

        onDispose {
            scanner.close()
            if (cameraProviderFuture.isDone) {
                cameraProviderFuture.get().unbindAll()
            }
        }
    }

    AndroidView(
        modifier = modifier,
        factory = { previewView }
    )
}
