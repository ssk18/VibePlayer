package com.ssk.vibeplayer.core.permission

import android.Manifest
import android.os.Build

/**
 * Returns the required audio permission based on Android version.
 * - Android 13+ (API 33): READ_MEDIA_AUDIO
 * - Android 12 and below: READ_EXTERNAL_STORAGE
 */
fun getRequiredAudioPermission(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_AUDIO
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }
}