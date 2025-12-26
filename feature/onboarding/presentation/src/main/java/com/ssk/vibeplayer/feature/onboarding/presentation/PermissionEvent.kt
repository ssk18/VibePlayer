package com.ssk.vibeplayer.feature.onboarding.presentation

/**
 * One-time events emitted by PermissionViewModel.
 * These are consumed once and not replayed on configuration changes.
 */
sealed interface PermissionEvent {
    /** Request the system permission dialog */
    data object RequestPermission : PermissionEvent

    /** Navigate to the library screen */
    data object NavigateToLibrary : PermissionEvent
}