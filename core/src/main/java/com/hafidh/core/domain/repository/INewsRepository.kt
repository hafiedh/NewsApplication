package com.hafidh.core.domain.repository

import com.hafidh.core.data.Resource
import com.hafidh.core.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface INewsRepository {

    fun getAllBreakingNews(): Flow<Resource<List<Article>>>

    fun getFavoriteNews(): Flow<List<Article>>

    fun setFavoriteNews(article: Article, state: Boolean)
}