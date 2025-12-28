package com.ssk.vibeplayer.feature.scanner.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ssk.vibeplayer.core.presentation.designsystem.components.VibePlayerScaffold
import com.ssk.vibeplayer.core.presentation.designsystem.components.VibePlayerTopBar
import com.ssk.vibeplayer.core.presentation.designsystem.theme.VibePlayerTheme
import com.ssk.vibeplayer.core.presentation.ui.ObserveAsEvents
import com.ssk.vibeplayer.feature.scanner.presentation.R
import com.ssk.vibeplayer.feature.scanner.presentation.ScanningViewModel
import com.ssk.vibeplayer.feature.scanner.presentation.components.RadarScanAnimation
import com.ssk.vibeplayer.feature.scanner.presentation.scanninghandler.ScanningEvent
import com.ssk.vibeplayer.feature.scanner.presentation.scanninghandler.ScanningState

@Composable
fun ScanningScreenRoot(
    onNavigateToTrackList: () -> Unit,
    viewModel: ScanningViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onScreenEntered()
    }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            ScanningEvent.NavigateToTrackList -> {
                onNavigateToTrackList()
            }
        }
    }

    ScanningScreen(
        state = state,
        onScanAgain = viewModel::onScanAgain
    )
}

@Composable
fun ScanningScreen(
    state: ScanningState,
    onScanAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    VibePlayerScaffold(
        topBar = {
            VibePlayerTopBar(
                topBarLogo = R.drawable.ic_topbar_logo,
                actions = R.drawable.ic_actions,
                onActionClick = {}
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.isScanning) {
                ScanningContent()
            } else if (!state.filesFound) {
                NoFilesFoundContent(onScanAgain = onScanAgain)
            }
        }
    }
}

@Composable
private fun ScanningContent() {
    RadarScanAnimation(
        modifier = Modifier.size(150.dp)
    )

    Spacer(modifier = Modifier.height(24.dp))

    Text(
        text = "Scanning your device for music...",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
    )
}

@Composable
private fun NoFilesFoundContent(
    onScanAgain: () -> Unit
) {
    Icon(
        painter = painterResource(R.drawable.ic_scan),
        contentDescription = null,
        modifier = Modifier.size(80.dp),
        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    )

    Spacer(modifier = Modifier.height(24.dp))

    Text(
        text = "No music found",
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSurface
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "Try scanning again or check your folders.",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(32.dp))

    Button(
        onClick = onScanAgain,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(text = "Scan again")
    }
}

@Preview(showBackground = true)
@Composable
private fun ScanningScreenPreview() {
    VibePlayerTheme {
        ScanningScreen(
            state = ScanningState(isScanning = true),
            onScanAgain = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NoFilesFoundPreview() {
    VibePlayerTheme {
        ScanningScreen(
            state = ScanningState(isScanning = false, filesFound = false),
            onScanAgain = {}
        )
    }
}
