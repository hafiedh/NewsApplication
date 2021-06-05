package com.hafidh.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hafidh.core.data.source.remote.network.response.Source

@Entity(tableName = "article")
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?,
    var isfavorite: Boolean = false
)
