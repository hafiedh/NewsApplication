package com.hafidh.core.domain.usecase

import com.hafidh.core.data.Resource
import com.hafidh.core.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface BreakingNewsUseCase {

    fun getAllBreakingNews(): Flow<Resource<List<Article>>>

    fun getFavoriteNews(): Flow<List<Article>>

    fun setFavoriteNews(article: Article, state: Boolean)
}