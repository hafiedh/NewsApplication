package com.hafidh.core.data.source.remote.network.response


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NewsResponse(
    @SerializedName("articles")
    val articles: List<ArticleResponse>
)