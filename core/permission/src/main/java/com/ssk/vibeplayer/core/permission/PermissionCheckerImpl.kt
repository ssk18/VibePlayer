package com.ssk.vibeplayer.core.permission

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of [PermissionChecker] that uses Android's permission APIs.
 */
@Singleton
class PermissionCheckerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : PermissionChecker {

    override fun hasAudioPermission(): Boolean {
        val permission = getRequiredAudioPermission()
        return ContextCompat.checkSelfPermission(context, permission) ==
            PackageManager.PERMISSION_GRANTED
    }
}