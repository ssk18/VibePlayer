package com.ssk.vibeplayer.feature.onboarding.presentation.screen

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ssk.vibeplayer.core.permission.getRequiredAudioPermission
import com.ssk.vibeplayer.core.presentation.designsystem.components.PrimaryButton
import com.ssk.vibeplayer.core.presentation.designsystem.theme.VibePlayerTheme
import com.ssk.vibeplayer.core.presentation.designsystem.theme.backgroundSurface
import com.ssk.vibeplayer.core.presentation.ui.ObserveAsEvents
import com.ssk.vibeplayer.feature.onboarding.presentation.PermissionAction
import com.ssk.vibeplayer.feature.onboarding.presentation.PermissionEvent
import com.ssk.vibeplayer.feature.onboarding.presentation.PermissionsViewModel
import com.ssk.vibeplayer.feature.onboarding.presentation.R


@Composable
fun PermissionScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: PermissionsViewModel = hiltViewModel(),
    onNavigateToScanResults: () -> Unit
) {
    val context = LocalContext.current
    val audioPermission = getRequiredAudioPermission()

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        viewModel.onAction(PermissionAction.OnPermissionResult(granted = isGranted))
    }

    val state by viewModel.permissionState.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.permissionEvents) { event ->
        when (event) {
            PermissionEvent.NavigateToLibrary -> onNavigateToScanResults()
            PermissionEvent.RequestPermission -> permissionLauncher.launch(audioPermission)
            PermissionEvent.OpenSettings -> {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                }
                context.startActivity(intent)
            }
        }
    }

    if (state.showDeniedDialog) {
        val showOpenSettings = state.denialCount >= 2
        AlertDialog(
            onDismissRequest = { viewModel.onAction(PermissionAction.OnDeniedDialogDismiss) },
            title = { Text(text = "Permission Required") },
            text = {
                Text(
                    text = if (showOpenSettings) {
                        "VibePlayer needs access to your music files to function properly. " +
                                "Please enable the permission in Settings."
                    } else {
                        "VibePlayer needs access to your music files to function properly. " +
                                "Without this permission, the app cannot build your music library or play songs."
                    }
                )
            },
            confirmButton = {
                if (showOpenSettings) {
                    TextButton(onClick = { viewModel.onAction(PermissionAction.OnOpenSettings) }) {
                        Text("Open Settings")
                    }
                } else {
                    TextButton(onClick = { viewModel.onAction(PermissionAction.OnDeniedDialogRetry) }) {
                        Text("Try Again")
                    }
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.onAction(PermissionAction.OnDeniedDialogDismiss) }) {
                    Text("OK")
                }
            }
        )
    }

    PermissionScreen(
        onAction = viewModel::onAction,
        modifier = modifier
    )
}

@Composable
fun PermissionScreen(
    modifier: Modifier = Modifier,
    onAction: (PermissionAction) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundSurface)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "player logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(12.dp)
        )
        Text(
            text = stringResource(R.string.vibeplayer),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Text(
            text = "VibePlayer needs access to your music files to build\n" +
                    "your library and play songs",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        PrimaryButton(
            text = "Allow Access",
            isEnabled = true,
            onClick = {
                onAction(PermissionAction.OnAllowAccess)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PermissionScreenPreview() {
    VibePlayerTheme {
        PermissionScreen(
            onAction = {}
        )
    }
}
