package com.ssk.vibeplayer.feature.onboarding.presentation

sealed interface PermissionAction {
    /** User clicks "Allow Access" button */
    data object OnAllowAccess : PermissionAction

    /** System permission result received */
    data class OnPermissionResult(val granted: Boolean) : PermissionAction

    /** User clicks "OK" on denied dialog - dismiss and stay on screen */
    data object OnDeniedDialogDismiss : PermissionAction

    /** User clicks "Try Again" on denied dialog - retry permission request */
    data object OnDeniedDialogRetry : PermissionAction

    /** User clicks "Open Settings" on denied dialog */
    data object OnOpenSettings : PermissionAction
}