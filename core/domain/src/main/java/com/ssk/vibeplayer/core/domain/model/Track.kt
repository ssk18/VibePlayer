package com.ssk.vibeplayer.core.domain.model

data class Track(
    val id: Long,
    val title: String,
    val artist: String,
    val duration: Long,        // Duration in milliseconds
    val filePath: String,
    val albumArtUri: String?,
    val fileSize: Long         // File size in bytes
) {
    val formattedDuration: String
        get() {
            val totalSeconds = duration / 1000
            val minutes = totalSeconds / 60
            val seconds = totalSeconds % 60
            return String.format("%d:%02d", minutes, seconds)
        }
}
