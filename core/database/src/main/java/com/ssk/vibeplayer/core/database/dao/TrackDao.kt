package com.ssk.vibeplayer.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.ssk.vibeplayer.core.database.entity.TrackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {
    @Query("SELECT * FROM tracks ORDER BY title ASC")
    fun getAllTracks(): Flow<List<TrackEntity>>

    @Query("SELECT * FROM tracks WHERE id = :trackId")
    suspend fun getTrackById(trackId: Long): TrackEntity?

    @Upsert
    suspend fun insertTracks(tracks: List<TrackEntity>)

    @Query("DELETE FROM tracks WHERE id = :trackId")
    suspend fun deleteTrack(trackId: Long)

    @Query("DELETE FROM tracks")
    suspend fun deleteAllTracks()

    @Query("SELECT filePath FROM tracks")
    suspend fun getAllFilePaths(): List<String>

    @Query("DELETE FROM tracks WHERE filePath = :filePath")
    suspend fun deleteTrackByPath(filePath: String)
}
