package com.ssk.vibeplayer.feature.scanner.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssk.vibeplayer.feature.scanner.domain.repository.TrackRepository
import com.ssk.vibeplayer.feature.scanner.presentation.trackhandler.ScanResultEvent
import com.ssk.vibeplayer.feature.scanner.presentation.trackhandler.ScanResultsAction
import com.ssk.vibeplayer.feature.scanner.presentation.trackhandler.TrackScreenState
import com.ssk.vibeplayer.feature.scanner.presentation.trackhandler.toTrackUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TracksViewModel @Inject constructor(
    private val trackRepository: TrackRepository
) : ViewModel() {

    private val _trackState = MutableStateFlow(TrackScreenState())
    val trackState = _trackState.asStateFlow()

    private val _events = Channel<ScanResultEvent>()
    val events = _events.receiveAsFlow()

    init {
        viewModelScope.launch {
            trackRepository.getAllTracks()
                .collect { tracks ->
                    _trackState.update {
                        it.copy(
                            tracks = tracks.map { track -> track.toTrackUi() }.toPersistentList()
                        )
                    }
                }
        }
    }

    fun onAction(action: ScanResultsAction) {
        when (action) {
            ScanResultsAction.OnScan -> {
                _events.trySend(ScanResultEvent.NavigateToScanScreen)
            }
        }
    }

}