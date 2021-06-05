package com.hafidh.core.data

import com.hafidh.core.data.source.local.LocalRepository
import com.hafidh.core.data.source.remote.network.ApiResponse
import com.hafidh.core.data.source.remote.network.RemoteRepository
import com.hafidh.core.data.source.remote.network.response.ArticleResponse
import com.hafidh.core.domain.model.Article
import com.hafidh.core.domain.repository.INewsRepository
import com.hafidh.core.utis.AppExecutors
import com.hafidh.core.utis.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreakingNewsRepository @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
    private val appExecutors: AppExecutors
) : INewsRepository {
    override fun getAllBreakingNews(): Flow<Resource<List<Article>>> =
        object : NetworkBoundResource<List<Article>, List<ArticleResponse>>() {

            override fun loadFromDB(): Flow<List<Article>> {
                return localRepository.getAllArticle().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Article>?) =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<ArticleResponse>>> =
                remoteRepository.getAllBreakingNews()

            override suspend fun saveCallResult(data: List<ArticleResponse>) {
                val articleList = DataMapper.mapResponseToEntities(data)
                localRepository.insertArticle(articleList)
            }
        }.asFlow()


    override fun getFavoriteNews(): Flow<List<Article>> {
        return localRepository.getFavoriteArticle().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteNews(article: Article, state: Boolean) {
        val articleEntities = DataMapper.mapDomainToEntities(article)
        appExecutors.diskIO().execute {
            localRepository.setFavoriteArticle(articleEntities, state)
        }
    }
}