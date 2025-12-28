package com.ssk.vibeplayer.feature.scanner.data.di

import com.ssk.vibeplayer.feature.scanner.data.repository.TrackRepositoryImpl
import com.ssk.vibeplayer.feature.scanner.domain.repository.TrackRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindTrackRepository(
        trackRepositoryImpl: TrackRepositoryImpl
    ): TrackRepository
}