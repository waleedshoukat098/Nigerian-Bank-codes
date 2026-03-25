package com.techinnovation.nigerianbankcodes.feature.scanner

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.util.Patterns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.weight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.techinnovation.nigerianbankcodes.core.designsystem.components.PermissionPromptCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScannerScreen(viewModel: ScannerViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var hasCameraPermission by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableStateOf(0) }
    val context = LocalContext.current
    val snackState = remember { SnackbarHostState() }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasCameraPermission = granted
        if (!granted) viewModel.emitInfo("Camera permission denied")
    }

    val imagePicker = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri == null) {
            viewModel.emitInfo("Image selection cancelled")
            return@rememberLauncherForActivityResult
        }
        viewModel.setProcessing(true)
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
        val image = InputImage.fromFilePath(context, uri)
        recognizer.process(image)
            .addOnSuccessListener { result ->
                if (result.text.isBlank()) {
                    viewModel.onError("No text found in selected image")
                } else {
                    viewModel.onOcrExtracted(result.text)
                }
            }
            .addOnFailureListener { viewModel.onError("OCR failed: ${it.localizedMessage ?: "Unknown error"}") }
    }

    LaunchedEffect(Unit) { permissionLauncher.launch(Manifest.permission.CAMERA) }

    LaunchedEffect(Unit) {
        viewModel.events.collect { snackState.showSnackbar(it) }
    }

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackState) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Scanner", style = MaterialTheme.typography.headlineSmall)
            Text("QR + OCR tools with save, share and quick actions")

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = { selectedTab = 0 }) { Text("QR / Barcode") }
                OutlinedButton(onClick = { selectedTab = 1 }) { Text("Document OCR") }
            }

            if (selectedTab == 0) {
                if (hasCameraPermission) {
                    QrCameraPreview(onResult = viewModel::onQrResultDetected)
                } else {
                    PermissionPromptCard(
                        title = "Camera Access Needed",
                        description = "We only use camera frames locally to read QR and barcodes.",
                        ctaText = "Grant Permission",
                        onCtaClick = { permissionLauncher.launch(Manifest.permission.CAMERA) }
                    )
                }

                ResultActionCard(
                    content = state.qrResult,
                    onCopy = {
                        val value = state.qrResult ?: return@ResultActionCard
                        val clip = android.content.ClipData.newPlainText("qr", value)
                        (context.getSystemService(android.content.Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager).setPrimaryClip(clip)
                        viewModel.emitInfo("Copied")
                    },
                    onShare = {
                        val value = state.qrResult ?: return@ResultActionCard
                        context.startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, value)
                        }, "Share result"))
                    },
                    onOpen = {
                        val value = state.qrResult ?: return@ResultActionCard
                        if (Patterns.WEB_URL.matcher(value).matches()) {
                            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(value)))
                        } else {
                            viewModel.emitInfo("Result is not a valid URL")
                        }
                    },
                    onSave = viewModel::saveQrResult
                )
            } else {
                Button(onClick = { imagePicker.launch("image/*") }) { Text("Pick Image for OCR") }
                if (state.isProcessing) Text("Processing image…")
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    value = state.ocrResult,
                    onValueChange = viewModel::onOcrExtracted,
                    maxLines = 8,
                    label = { Text("Extracted text") }
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = viewModel::saveOcrResult) { Text("Save") }
                    OutlinedButton(onClick = {
                        if (state.ocrResult.isBlank()) {
                            viewModel.emitInfo("Nothing to share")
                            return@OutlinedButton
                        }
                        val sendIntent = Intent(Intent.ACTION_SEND).apply {
                            putExtra(Intent.EXTRA_TEXT, state.ocrResult)
                            type = "text/plain"
                        }
                        context.startActivity(Intent.createChooser(sendIntent, "Share OCR"))
                    }) { Text("Share") }
                }
            }

            state.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        }
    }
}

@Composable
private fun QrCameraPreview(onResult: (String) -> Unit) {
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
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
        factory = { previewView }
    )
}

@Composable
private fun ResultActionCard(
    content: String?,
    onCopy: () -> Unit,
    onShare: () -> Unit,
    onOpen: () -> Unit,
    onSave: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Scan Result", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Text(content ?: "No code detected yet. Point camera at a QR or barcode.")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = onCopy, enabled = !content.isNullOrBlank()) { Text("Copy") }
                OutlinedButton(onClick = onShare, enabled = !content.isNullOrBlank()) { Text("Share") }
                OutlinedButton(onClick = onOpen, enabled = !content.isNullOrBlank()) { Text("Open") }
                Button(onClick = onSave, enabled = !content.isNullOrBlank()) { Text("Save") }
            }
        }
    }
}
