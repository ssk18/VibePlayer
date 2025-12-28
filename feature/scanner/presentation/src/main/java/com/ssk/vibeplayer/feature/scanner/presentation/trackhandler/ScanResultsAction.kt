package com.ssk.vibeplayer.feature.scanner.presentation.trackhandler

sealed interface ScanResultsAction {
    data object OnScan: ScanResultsAction
}