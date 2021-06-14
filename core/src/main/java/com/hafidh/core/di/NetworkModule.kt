package com.hafidh.core.di

import com.hafidh.core.BuildConfig
import com.hafidh.core.data.source.remote.network.ApiService
import com.hafidh.core.utis.Constans.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.CertificatePinner
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
    fun provideOkHttp():OkHttpClient{
        val hostName = "newsapi.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostName, "sha256/c5XTqkOxoXqc60M3HuT9fgmfeexiP2+Q8BD3+6abZYU=")
            .add(hostName, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .add(hostName, "sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=")
            .build()
        val okHttpClient = OkHttpClient.Builder()
            if(BuildConfig.DEBUG){
                okHttpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            okHttpClient.connectTimeout(120,TimeUnit.SECONDS)
            okHttpClient.readTimeout(120, TimeUnit.SECONDS)
            okHttpClient.certificatePinner(certificatePinner)

        return okHttpClient.build()
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