package com.ssk.vibeplayer.core.data.mapper

import com.ssk.vibeplayer.core.database.entity.TrackEntity
import com.ssk.vibeplayer.core.domain.model.Track

fun Track.toEntity(): TrackEntity {
    return TrackEntity(
        id = id,
        title = title,
        artist = artist,
        duration = duration,
        filePath = filePath,
        albumArtUri = albumArtUri,
        fileSize = fileSize
    )
}

fun TrackEntity.toTrack(): Track {
    return Track(
        id = id,
        title = title,
        artist = artist,
        duration = duration,
        filePath = filePath,
        albumArtUri = albumArtUri,
        fileSize = fileSize
    )
}

fun List<Track>.toEntityList(): List<TrackEntity> = map { it.toEntity() }

fun List<TrackEntity>.toTrackList(): List<Track> = map { it.toTrack() }