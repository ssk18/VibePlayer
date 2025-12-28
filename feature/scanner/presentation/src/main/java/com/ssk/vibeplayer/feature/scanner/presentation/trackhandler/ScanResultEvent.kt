package com.ssk.vibeplayer.feature.scanner.presentation.trackhandler

sealed interface ScanResultEvent {
    data object NavigateToScanScreen: ScanResultEvent
}