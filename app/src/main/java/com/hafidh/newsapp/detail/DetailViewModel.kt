package com.hafidh.newsapp.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.hafidh.core.domain.model.Article
import com.hafidh.core.domain.usecase.BreakingNewsUseCase


class DetailViewModel @ViewModelInject constructor(private val breakingNewsUseCase: BreakingNewsUseCase) : ViewModel() {
    fun setFavoriteBreakingNews(article: Article, newState: Boolean) {
        breakingNewsUseCase.setFavoriteNews(article,newState)
    }
}