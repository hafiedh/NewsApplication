package com.hafidh.core.di

import com.hafidh.core.BuildConfig
import com.hafidh.core.data.source.remote.network.ApiService
import com.hafidh.core.utis.Constans.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val okhttpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            okhttpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        okhttpClient.connectTimeout(120, TimeUnit.SECONDS)
        okhttpClient.readTimeout(120, TimeUnit.SECONDS)
        return okhttpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofitService(client: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }

}