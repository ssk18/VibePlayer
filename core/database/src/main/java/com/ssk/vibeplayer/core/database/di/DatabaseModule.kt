package com.ssk.vibeplayer.core.database.di

import androidx.room.Room
import com.ssk.vibeplayer.core.database.VibePlayerDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            VibePlayerDatabase::class.java,
            "vibeplayer_database"
        ).build()
    }

    single { get<VibePlayerDatabase>().trackDao() }
}
