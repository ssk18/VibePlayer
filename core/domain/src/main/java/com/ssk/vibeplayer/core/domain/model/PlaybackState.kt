package com.ssk.vibeplayer.core.domain.model

sealed interface PlaybackState {
    data object Idle : PlaybackState
    data class Playing(
        val track: Track,
        val progress: Long,      // Current position in milliseconds
        val duration: Long       // Total duration in milliseconds
    ) : PlaybackState
    data class Paused(
        val track: Track,
        val progress: Long,
        val duration: Long
    ) : PlaybackState
}
