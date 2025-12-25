package com.ssk.vibeplayer.core.media.di

import android.content.Context
import com.ssk.vibeplayer.core.media.player.AudioPlayer
import com.ssk.vibeplayer.core.media.player.ExoAudioPlayer
import com.ssk.vibeplayer.core.media.scanner.MediaMetadataScanner
import com.ssk.vibeplayer.core.media.scanner.MediaScanner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MediaModule {

    @Provides
    @Singleton
    fun provideMediaScanner(
        @ApplicationContext context: Context
    ): MediaScanner {
        return MediaMetadataScanner(context)
    }

    @Provides
    @Singleton
    fun provideAudioPlayer(
        @ApplicationContext context: Context
    ): AudioPlayer {
        return ExoAudioPlayer(context)
    }
}
