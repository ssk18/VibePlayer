package com.ssk.vibeplayer.core.domain.repository

import com.ssk.vibeplayer.core.domain.model.Track
import kotlinx.coroutines.flow.Flow

interface TrackRepository {
    fun getAllTracks(): Flow<List<Track>>
    suspend fun getTrackById(id: Long): Track?
    suspend fun insertTracks(tracks: List<Track>)
    suspend fun deleteTrack(trackId: Long)
    suspend fun deleteAllTracks()
    suspend fun validateTracks(): Int  // Returns count of removed tracks
}
