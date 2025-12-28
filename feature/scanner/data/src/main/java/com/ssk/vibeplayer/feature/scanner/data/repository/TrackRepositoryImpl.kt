package com.ssk.vibeplayer.feature.scanner.data.repository

import com.ssk.vibeplayer.core.data.mapper.toEntityList
import com.ssk.vibeplayer.core.data.mapper.toTrack
import com.ssk.vibeplayer.core.data.mapper.toTrackList
import com.ssk.vibeplayer.core.database.dao.TrackDao
import com.ssk.vibeplayer.core.domain.model.Track
import com.ssk.vibeplayer.feature.scanner.domain.repository.TrackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.File
import javax.inject.Inject

class TrackRepositoryImpl @Inject constructor(
    private val trackDao: TrackDao
) : TrackRepository {

    override fun getAllTracks(): Flow<List<Track>> {
        return trackDao.getAllTracks().map { entities ->
            entities.toTrackList()
        }
    }

    override suspend fun getTrackById(id: Long): Track? {
        return trackDao.getTrackById(id)?.toTrack()
    }

    override suspend fun insertTracks(tracks: List<Track>) {
        trackDao.insertTracks(tracks.toEntityList())
    }

    override suspend fun deleteTrack(trackId: Long) {
        trackDao.deleteTrack(trackId)
    }

    override suspend fun deleteAllTracks() {
        trackDao.deleteAllTracks()
    }

    override suspend fun validateTracks(): Int {
        val filePaths = trackDao.getAllFilePaths()
        var removedCount = 0

        filePaths.forEach { path ->
            if (!File(path).exists()) {
                trackDao.deleteTrackByPath(path)
                removedCount++
            }
        }

        return removedCount
    }
}