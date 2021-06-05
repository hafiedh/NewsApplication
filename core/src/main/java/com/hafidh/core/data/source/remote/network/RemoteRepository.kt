package com.hafidh.core.data.source.remote.network

import com.hafidh.core.data.source.remote.network.response.ArticleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllBreakingNews(): Flow<ApiResponse<List<ArticleResponse>>> {
        return flow {
            try {
                val response = apiService.getBreakingNews()
                val data = response.articles
                if(data.isNotEmpty()){
                    emit(ApiResponse.Success(data))
                }else{
                    emit(ApiResponse.Empty)
                }
            } catch (e:Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}