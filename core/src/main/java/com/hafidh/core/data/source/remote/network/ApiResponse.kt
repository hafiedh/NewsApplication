package com.hafidh.core.data.source.remote.network

sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val error: String) : ApiResponse<Nothing>()
    object Empty : ApiResponse<Nothing>()
}