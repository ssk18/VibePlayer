package com.ssk.vibeplayer.core.database.di

import android.content.Context
import androidx.room.Room
import com.ssk.vibeplayer.core.database.VibePlayerDatabase
import com.ssk.vibeplayer.core.database.dao.TrackDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): VibePlayerDatabase {
        return Room.databaseBuilder(
            context,
            VibePlayerDatabase::class.java,
            "vibeplayer_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTrackDao(database: VibePlayerDatabase): TrackDao {
        return database.trackDao()
    }
}