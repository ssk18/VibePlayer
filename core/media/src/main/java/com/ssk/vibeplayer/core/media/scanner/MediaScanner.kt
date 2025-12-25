package com.ssk.vibeplayer.core.media.scanner

import com.ssk.vibeplayer.core.domain.model.ScanFilter
import com.ssk.vibeplayer.core.domain.model.Track
import kotlinx.coroutines.flow.Flow

interface MediaScanner {
    suspend fun scanForMusic(filter: ScanFilter): Flow<List<Track>>
    suspend fun scanForMusicSync(filter: ScanFilter): List<Track>
}
