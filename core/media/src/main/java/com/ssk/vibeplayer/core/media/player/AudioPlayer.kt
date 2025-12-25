package com.ssk.vibeplayer.core.media.player

import com.ssk.vibeplayer.core.domain.model.PlaybackState
import com.ssk.vibeplayer.core.domain.model.Track
import kotlinx.coroutines.flow.Flow

interface AudioPlayer {
    val playbackState: Flow<PlaybackState>

    fun play(track: Track, trackList: List<Track>)
    fun pause()
    fun resume()
    fun stop()
    fun skipToNext()
    fun skipToPrevious()
    fun seekTo(position: Long)
    fun release()
}
