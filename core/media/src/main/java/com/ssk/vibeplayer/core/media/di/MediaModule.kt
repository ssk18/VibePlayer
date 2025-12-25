package com.ssk.vibeplayer.core.media.di

import com.ssk.vibeplayer.core.media.player.AudioPlayer
import com.ssk.vibeplayer.core.media.player.ExoAudioPlayer
import com.ssk.vibeplayer.core.media.scanner.MediaMetadataScanner
import com.ssk.vibeplayer.core.media.scanner.MediaScanner
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val mediaModule = module {
    single<MediaScanner> { MediaMetadataScanner(androidContext()) }
    single<AudioPlayer> { ExoAudioPlayer(androidContext()) }
}
