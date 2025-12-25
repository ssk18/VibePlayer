package com.ssk.vibeplayer.core.media.scanner

import android.content.Context
import android.media.MediaMetadataRetriever
import android.provider.MediaStore
import com.ssk.vibeplayer.core.domain.model.ScanFilter
import com.ssk.vibeplayer.core.domain.model.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class MediaMetadataScanner(
    private val context: Context
) : MediaScanner {

    override suspend fun scanForMusic(filter: ScanFilter): Flow<List<Track>> = flow {
        emit(scanForMusicSync(filter))
    }.flowOn(Dispatchers.IO)

    override suspend fun scanForMusicSync(filter: ScanFilter): List<Track> = withContext(Dispatchers.IO) {
        val tracks = mutableListOf<Track>()
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.ALBUM_ID
        )

        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0 AND " +
                "${MediaStore.Audio.Media.DURATION} >= ? AND " +
                "${MediaStore.Audio.Media.SIZE} >= ?"

        val selectionArgs = arrayOf(
            filter.minDurationMs.toString(),
            filter.minSizeBytes.toString()
        )

        val sortOrder = "${MediaStore.Audio.Media.TITLE} ASC"

        context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)
            val albumIdColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val title = cursor.getString(titleColumn) ?: "Unknown"
                val artist = cursor.getString(artistColumn) ?: "Unknown Artist"
                val duration = cursor.getLong(durationColumn)
                val filePath = cursor.getString(dataColumn)
                val fileSize = cursor.getLong(sizeColumn)
                val albumId = cursor.getLong(albumIdColumn)

                val albumArtUri = "content://media/external/audio/albumart/$albumId"

                tracks.add(
                    Track(
                        id = id,
                        title = title,
                        artist = artist,
                        duration = duration,
                        filePath = filePath,
                        albumArtUri = albumArtUri,
                        fileSize = fileSize
                    )
                )
            }
        }

        tracks
    }
}
