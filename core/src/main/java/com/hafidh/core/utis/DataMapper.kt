package com.hafidh.core.utis

import com.hafidh.core.data.source.local.entity.Article
import com.hafidh.core.data.source.remote.network.response.ArticleResponse
import com.hafidh.core.data.source.remote.network.response.Source

object DataMapper {
    fun mapResponseToEntities(input: List<ArticleResponse>): List<Article> {
        val articleList = ArrayList<Article>()
        input.map {
            val entities = Article(
                id = null,
                author = it.author,
                content = it.content,
                description = it.description,
                publishedAt = it.publishedAt,
                source = it.source,
                title = it.title,
                url = it.url,
                urlToImage = it.urlToImage,
                isfavorite = false
            )
            articleList.add(entities)
        }
        return articleList
    }

    fun mapEntitiesToDomain(input: List<Article>): List<com.hafidh.core.domain.model.Article> =
        input.map {
            com.hafidh.core.domain.model.Article(
                id = it.id,
                author = it.author,
                content = it.content,
                description = it.description,
                publishedAt = it.publishedAt,
                source = it.source?.let { source -> convertSourceToString(source) },
                title = it.title,
                url = it.url,
                urlToImage = it.urlToImage,
                isFavorite = it.isfavorite
            )
        }

    fun mapDomainToEntities(input: com.hafidh.core.domain.model.Article): Article = Article(
        id = input.id,
        author = input.author,
        content = input.content,
        description = input.description,
        publishedAt = input.publishedAt,
        source = input.source?.let { convertToSource(it) },
        title = input.title,
        url = input.url,
        urlToImage = input.urlToImage,
        isfavorite = input.isFavorite
    )

   private fun convertSourceToString(input: Source): String = input.name
   private fun convertToSource(input: String): Source = Source(input, input)
}