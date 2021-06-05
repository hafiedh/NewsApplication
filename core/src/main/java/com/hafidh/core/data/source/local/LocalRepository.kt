package com.hafidh.core.data.source.local

import com.hafidh.core.data.source.local.db.ArticleDao
import com.hafidh.core.data.source.local.entity.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(private val articleDao: ArticleDao) {
    fun getAllArticle(): Flow<List<Article>> = articleDao.getAllArticlesData()
    fun getFavoriteArticle(): Flow<List<Article>> = articleDao.getFavoriteArticle()
    fun setFavoriteArticle(article: Article, state:Boolean){
        article.isfavorite = state
        articleDao.updateFavoriteArticle(article)
    }
    suspend fun insertArticle(article: List<Article>) = articleDao.insertArticle(article)
}