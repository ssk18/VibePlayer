package com.ssk.vibeplayer.core.media.scanner

import com.ssk.vibeplayer.core.domain.model.Track

interface MediaScanner {
    suspend fun scanForMusic(): List<Track>
}
