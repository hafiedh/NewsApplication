package com.hafidh.newsapp.di

import com.hafidh.core.domain.usecase.BreakingNewsUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface AppDependencies {
    fun breakingNewsUseCase(): BreakingNewsUseCase
}