package com.hafidh.newsapp.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hafidh.core.domain.usecase.BreakingNewsUseCase


class HomeViewModel @ViewModelInject constructor(breakingNewsUseCase: BreakingNewsUseCase): ViewModel()  {
        val breakingNews = breakingNewsUseCase.getAllBreakingNews().asLiveData()
}