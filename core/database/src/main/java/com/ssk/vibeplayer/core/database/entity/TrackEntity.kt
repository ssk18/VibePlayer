package com.ssk.vibeplayer.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracks")
data class TrackEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val artist: String,
    val duration: Long,
    val filePath: String,
    val albumArtUri: String?,
    val fileSize: Long
)
