package com.ssk.vibeplayer.core.domain.model

data class ScanFilter(
    val minDurationSeconds: Int = 30,   // 30s or 60s
    val minSizeKb: Int = 100            // 100KB or 500KB
) {
    val minDurationMs: Long
        get() = minDurationSeconds * 1000L

    val minSizeBytes: Long
        get() = minSizeKb * 1024L
}
