package com.hafidh.core.di

import com.hafidh.core.data.BreakingNewsRepository
import com.hafidh.core.domain.repository.INewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module(includes = [NetworkModule::class,DatabaseModule::class])
@InstallIn(ApplicationComponent::class)

abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(breakingNewsRepository: BreakingNewsRepository): INewsRepository

}