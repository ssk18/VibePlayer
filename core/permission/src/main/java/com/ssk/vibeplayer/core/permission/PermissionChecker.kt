package com.ssk.vibeplayer.core.permission

/**
 * Interface for checking audio permissions.
 * Abstracts permission checking to enable testing.
 */
interface PermissionChecker {
    /**
     * Checks if the app has audio permission.
     * @return true if permission is granted, false otherwise
     */
    fun hasAudioPermission(): Boolean
}