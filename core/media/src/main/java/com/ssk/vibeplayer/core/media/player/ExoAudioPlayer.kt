package com.ssk.vibeplayer.core.media.player

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.ssk.vibeplayer.core.domain.model.PlaybackState
import com.ssk.vibeplayer.core.domain.model.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ExoAudioPlayer(
    context: Context
) : AudioPlayer {

    private val exoPlayer: ExoPlayer = ExoPlayer.Builder(context).build()

    private val _playbackState = MutableStateFlow<PlaybackState>(PlaybackState.Idle)
    override val playbackState: Flow<PlaybackState> = _playbackState.asStateFlow()

    private var currentTrack: Track? = null
    private var trackList: List<Track> = emptyList()
    private var currentIndex: Int = -1
    private var progressJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.Main)

    init {
        exoPlayer.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                updatePlaybackState()
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                updatePlaybackState()
                if (isPlaying) {
                    startProgressUpdates()
                } else {
                    stopProgressUpdates()
                }
            }

            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                // Handle track change
            }
        })
    }

    private fun startProgressUpdates() {
        progressJob?.cancel()
        progressJob = scope.launch {
            while (isActive && exoPlayer.isPlaying) {
                updatePlaybackState()
                delay(1000)
            }
        }
    }

    private fun stopProgressUpdates() {
        progressJob?.cancel()
        progressJob = null
    }

    private fun updatePlaybackState() {
        val track = currentTrack ?: return

        _playbackState.value = when {
            exoPlayer.isPlaying -> PlaybackState.Playing(
                track = track,
                progress = exoPlayer.currentPosition,
                duration = exoPlayer.duration
            )
            exoPlayer.playbackState == Player.STATE_READY && !exoPlayer.isPlaying -> PlaybackState.Paused(
                track = track,
                progress = exoPlayer.currentPosition,
                duration = exoPlayer.duration
            )
            else -> PlaybackState.Idle
        }
    }

    override fun play(track: Track, trackList: List<Track>) {
        this.trackList = trackList
        this.currentIndex = trackList.indexOfFirst { it.id == track.id }
        this.currentTrack = track

        val mediaItem = MediaItem.fromUri(track.filePath)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
    }

    override fun pause() {
        exoPlayer.pause()
    }

    override fun resume() {
        exoPlayer.play()
    }

    override fun stop() {
        exoPlayer.stop()
        _playbackState.value = PlaybackState.Idle
        currentTrack = null
    }

    override fun skipToNext() {
        if (currentIndex < trackList.size - 1) {
            currentIndex++
            val nextTrack = trackList[currentIndex]
            play(nextTrack, trackList)
        }
    }

    override fun skipToPrevious() {
        if (currentIndex > 0) {
            currentIndex--
            val previousTrack = trackList[currentIndex]
            play(previousTrack, trackList)
        }
    }

    override fun seekTo(position: Long) {
        exoPlayer.seekTo(position)
    }

    override fun release() {
        stopProgressUpdates()
        exoPlayer.release()
    }
}
