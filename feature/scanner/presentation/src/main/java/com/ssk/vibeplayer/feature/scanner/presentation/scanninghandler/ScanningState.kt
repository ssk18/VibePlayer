package com.ssk.vibeplayer.feature.scanner.presentation.scanninghandler

data class ScanningState(
    val isScanning: Boolean = true,
    val filesFound: Boolean = false,
    val trackCount: Int = 0
)

sealed interface ScanningEvent {
    data object NavigateToTrackList : ScanningEvent
}