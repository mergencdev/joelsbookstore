package com.mergenc.androidcore.domain

import com.google.gson.Gson
import com.mergenc.androidcore.data.remote.ApiError
import com.mergenc.androidcore.data.remote.ApiResult

sealed class UiError<out T> {

    data class NoInternet(val message: String? = null) : UiError<Nothing>()

    data class Authentication<T>(val errorBody: T? = null) : UiError<T>()

    data class Server<T>(val errorBody: T? = null) : UiError<T>()

    data class IO(val message: String? = null) : UiError<Nothing>()

}

inline fun <reified E> parseError(apiResult: ApiResult.Error): Result.Error<E> {
    return when (apiResult.error) {
        is ApiError.Server -> {
            try {
                val error = Gson().fromJson(
                    apiResult.error.errorBody?.string(),
                    E::class.java
                )
                Result.Error(UiError.Server(error))
            } catch (e: Exception) {
                Result.Error(UiError.Server())
            }
        }

        is ApiError.Authentication -> {
            try {
                val error = Gson().fromJson(
                    apiResult.error.errorBody?.string(),
                    E::class.java
                )
                Result.Error(UiError.Authentication(error))
            } catch (e: Exception) {
                Result.Error(UiError.Authentication())
            }
        }
        is ApiError.IO -> Result.Error(UiError.IO(message = apiResult.error.message))
        is ApiError.NoInternet -> Result.Error(UiError.NoInternet(message = apiResult.error.message))
    }
}

