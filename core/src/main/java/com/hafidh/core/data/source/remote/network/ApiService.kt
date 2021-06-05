package com.hafidh.core.data.source.remote.network

import com.hafidh.core.BuildConfig.API_KEY
import com.hafidh.core.data.source.remote.network.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode : String = "id",
        @Query("page")
        pageNumber : Int = 1,
        @Query("apikey")
        apiKey : String = API_KEY
    ) : NewsResponse
}