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
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("news".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "News.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Singleton
    @Provides
    fun provideArticleDao(db: NewsDatabase): ArticleDao = db.articleDao()

}