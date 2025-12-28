package com.ssk.vibeplayer.feature.scanner.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ssk.vibeplayer.core.presentation.designsystem.components.VibePlayerScaffold
import com.ssk.vibeplayer.core.presentation.designsystem.components.VibePlayerTopBar
import com.ssk.vibeplayer.core.presentation.designsystem.theme.ArrowUp
import com.ssk.vibeplayer.core.presentation.designsystem.theme.VibePlayerTheme
import com.ssk.vibeplayer.core.presentation.ui.ObserveAsEvents
import com.ssk.vibeplayer.feature.scanner.presentation.R
import com.ssk.vibeplayer.feature.scanner.presentation.TracksViewModel
import com.ssk.vibeplayer.feature.scanner.presentation.components.TrackScreenItem
import com.ssk.vibeplayer.feature.scanner.presentation.trackhandler.ScanResultEvent
import com.ssk.vibeplayer.feature.scanner.presentation.trackhandler.ScanResultsAction
import com.ssk.vibeplayer.feature.scanner.presentation.trackhandler.TrackUi
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch

@Composable
fun TrackScreenRoot(
    viewModel: TracksViewModel = hiltViewModel(),
    onNavigateToScanScreen: () -> Unit
) {
    val state by viewModel.trackState.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            ScanResultEvent.NavigateToScanScreen -> {
                onNavigateToScanScreen()
            }
        }
    }

    TracksScreen(
        tracks = state.tracks,
        onScanClick = viewModel::onAction
    )
}

@Composable
fun TracksScreen(
    modifier: Modifier = Modifier,
    tracks: PersistentList<TrackUi>,
    onScanClick: (ScanResultsAction) -> Unit
) {
    val listState = rememberLazyListState()
    val showScrollUpButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 1
        }
    }
    val scope = rememberCoroutineScope()

    VibePlayerScaffold(
        topBar = {
            VibePlayerTopBar(
                topBarLogo = R.drawable.ic_topbar_logo,
                actions = R.drawable.ic_actions,
                onActionClick = {
                    onScanClick(ScanResultsAction.OnScan)
                }
            )
        },
        floatingActionButton = {
            AnimatedVisibility(visible = showScrollUpButton) {
                IconButton(
                    onClick = {
                        scope.launch {
                            listState.animateScrollToItem(0)
                        }
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier
                        .size(56.dp)
                ) {
                    Icon(
                        imageVector = ArrowUp,
                        contentDescription = "Scroll up"
                    )
                }
            }
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues = paddingValues),
            state = listState
        ) {
            items(
                tracks,
                key = {
                    it.id
                }
            ) { track ->
                TrackScreenItem(
                    track = track
                )
            }
        }
    }
}

@Preview
@Composable
fun TracksScreenPreview() {
    VibePlayerTheme {
        TracksScreen(
            tracks = persistentListOf(),
            onScanClick = {}
        )
    }
}