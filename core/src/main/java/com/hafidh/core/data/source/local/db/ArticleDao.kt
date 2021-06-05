package com.hafidh.core.data.source.local.db

import androidx.room.*
import com.hafidh.core.data.source.local.entity.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAllArticlesData(): Flow<List<Article>>

    @Query("SELECT * FROM article where isfavorite = 1")
    fun getFavoriteArticle(): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: List<Article>)

    @Update
    fun updateFavoriteArticle(article: Article)
}