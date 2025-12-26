package com.ssk.vibeplayer.feature.onboarding.presentation

/**
 * Permission status enum.
 */
enum class PermissionStatus {
    INITIAL,
    GRANTED,
    DENIED
}

/**
 * Represents the UI state for the permission screen.
 */
data class PermissionState(
    val status: PermissionStatus = PermissionStatus.INITIAL,
    val showDeniedDialog: Boolean = false,
    val denialCount: Int = 0
)