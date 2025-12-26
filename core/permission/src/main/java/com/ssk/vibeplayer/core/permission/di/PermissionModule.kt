package com.ssk.vibeplayer.core.permission.di

import com.ssk.vibeplayer.core.permission.PermissionChecker
import com.ssk.vibeplayer.core.permission.PermissionCheckerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PermissionModule {

    @Binds
    @Singleton
    abstract fun bindPermissionChecker(impl: PermissionCheckerImpl): PermissionChecker
}