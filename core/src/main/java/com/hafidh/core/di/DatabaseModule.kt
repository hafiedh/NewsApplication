package com.hafidh.core.di

import android.content.Context
import androidx.room.Room
import com.hafidh.core.data.source.local.db.ArticleDao
import com.hafidh.core.data.source.local.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase =
        Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "News.db"
        ).fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideArticleDao(db: NewsDatabase): ArticleDao = db.articleDao()

}