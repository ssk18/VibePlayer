package com.ssk.vibeplayer.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ssk.vibeplayer.core.database.dao.TrackDao
import com.ssk.vibeplayer.core.database.entity.TrackEntity

@Database(
    entities = [TrackEntity::class],
    version = 1,
    exportSchema = true
)
abstract class VibePlayerDatabase : RoomDatabase() {
    abstract fun trackDao(): TrackDao
}
