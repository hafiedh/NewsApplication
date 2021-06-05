package com.hafidh.core.data.source.remote.network.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Source(
    @SerializedName("id")
    val id: Any,
    @SerializedName("name")
    val name: String
)