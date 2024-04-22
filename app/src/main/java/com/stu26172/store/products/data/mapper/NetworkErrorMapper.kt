package com.stu26172.store.products.data.mapper

import com.stu26172.store.products.domain.model.ApiError
import com.stu26172.store.products.domain.model.NetworkError
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toNetworkError(): NetworkError {
    val error = when (this){
        is IOException -> ApiError.NetworkError
        is HttpException -> ApiError.UnknownResponse
        else -> ApiError.UnknownError
    }

    return NetworkError(
        error = error,
        t = this
    )
}