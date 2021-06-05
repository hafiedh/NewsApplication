package com.hafidh.newsapp.di

import com.hafidh.core.domain.usecase.BreakingNewsInteractor
import com.hafidh.core.domain.usecase.BreakingNewsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent


@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideNewsUseCase(breakingNewsInteractor: BreakingNewsInteractor): BreakingNewsUseCase


}