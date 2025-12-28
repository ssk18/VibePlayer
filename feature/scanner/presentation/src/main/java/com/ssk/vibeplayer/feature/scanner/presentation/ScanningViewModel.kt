package com.ssk.vibeplayer.feature.scanner.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssk.vibeplayer.core.media.scanner.MediaScanner
import com.ssk.vibeplayer.feature.scanner.domain.repository.TrackRepository
import com.ssk.vibeplayer.feature.scanner.presentation.scanninghandler.ScanningEvent
import com.ssk.vibeplayer.feature.scanner.presentation.scanninghandler.ScanningState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanningViewModel @Inject constructor(
    private val mediaScanner: MediaScanner,
    private val trackRepository: TrackRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ScanningState())
    val state: StateFlow<ScanningState> = _state.asStateFlow()

    private val _events = Channel<ScanningEvent>()
    val events = _events.receiveAsFlow()

    private var scanningJob: Job? = null

    fun onScreenEntered() {
        if (scanningJob?.isActive == true) return
        startScanning()
    }

    fun onScanAgain() {
        scanningJob?.cancel()
        startScanning()
    }

    private fun startScanning() {
        scanningJob = viewModelScope.launch {
            _state.update { it.copy(isScanning = true, filesFound = false, trackCount = 0) }
            val removedCount = trackRepository.validateTracks()
            val tracks = mediaScanner.scanForMusic()

            if (tracks.isEmpty()) {
                _state.update { it.copy(isScanning = false, filesFound = false, trackCount = 0) }
            } else {
                trackRepository.insertTracks(tracks)
                _state.update {
                    it.copy(isScanning = false, filesFound = true, trackCount = tracks.size)
                }
                delay(400)
                _events.trySend(ScanningEvent.NavigateToTrackList)
            }
        }
    }
}