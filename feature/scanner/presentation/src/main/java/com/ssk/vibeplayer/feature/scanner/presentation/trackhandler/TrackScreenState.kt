package com.ssk.vibeplayer.feature.scanner.presentation.trackhandler

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class TrackScreenState(
    val tracks: PersistentList<TrackUi> = persistentListOf()
)
