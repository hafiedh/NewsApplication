package com.hafidh.core.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hafidh.core.data.source.local.entity.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(Convert::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}