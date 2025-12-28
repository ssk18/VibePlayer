package com.ssk.vibeplayer.feature.scanner.presentation.trackhandler

import androidx.compose.runtime.Immutable
import com.ssk.vibeplayer.core.domain.model.Track

@Immutable
data class TrackUi(
    val id: Long,
    val title: String,
    val artist: String,
    val duration: Long,
    val filePath: String,
    val albumArtUri: String?,
    val fileSize: Long
) {
    val formattedDuration: String
        get() {
            val totalSeconds = duration / 1000
            val minutes = totalSeconds / 60
            val seconds = totalSeconds % 60
            return String.format("%d:%02d", minutes, seconds)
        }
}

fun Track.toTrackUi() = TrackUi(
    id = id,
    title = title,
    artist = artist,
    albumArtUri = albumArtUri,
    fileSize = fileSize,
    filePath = filePath,
    duration = duration
)

