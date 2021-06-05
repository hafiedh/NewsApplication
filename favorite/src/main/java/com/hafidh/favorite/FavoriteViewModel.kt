package com.hafidh.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hafidh.core.domain.usecase.BreakingNewsUseCase


class FavoriteViewModel (breakingNewsUseCase: BreakingNewsUseCase): ViewModel(){
    val favoriteBreakingNews = breakingNewsUseCase.getFavoriteNews().asLiveData()
}