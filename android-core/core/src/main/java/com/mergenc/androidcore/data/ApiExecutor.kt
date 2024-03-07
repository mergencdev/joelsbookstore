package com.mergenc.androidcore.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import com.mergenc.androidcore.data.remote.ApiError
import com.mergenc.androidcore.data.remote.ApiResult
import com.mergenc.androidcore.data.remote.Const.Companion.ERROR_CODE_UNAUTHORIZED
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException

interface ApiExecutor {

    suspend fun <T> execute(
        call: suspend () -> Response<T>,
        showLoadingDialog: Boolean = true,
        apiStateFlow: MutableStateFlow<LoadingState>
    ): ApiResult<T> {
        var result: ApiResult<T>
        if (showLoadingDialog) {
            awaitFrame()
            apiStateFlow.emit(LoadingState(Visibility.SHOW))
        }
        withContext(Dispatchers.IO) {
            val response: Response<T>
            try {
                response = call.invoke()
                if (showLoadingDialog) {
                    withContext(Dispatchers.Main) {
                        awaitFrame()
                        apiStateFlow.emit(LoadingState(Visibility.HIDE))
                    }
                }
                if (response.isSuccessful) {
                    result = ApiResult.Success(response.body())
                } else {
                    if (response.code() == ERROR_CODE_UNAUTHORIZED) {
                        result = ApiResult.Error(ApiError.Authentication(response.errorBody()))
                    } else {
                        result = ApiResult.Error(ApiError.Server(response.errorBody()))
                    }
                }
            } catch (exception: Exception) {
                if (showLoadingDialog) {
                    withContext(Dispatchers.Main) {
                        awaitFrame()
                        apiStateFlow.emit(LoadingState(Visibility.HIDE))
                    }
                }
                result = if (exception is ConnectException || exception is UnknownHostException) {
                    ApiResult.Error(ApiError.NoInternet(exception.message))
                } else {
                    ApiResult.Error(ApiError.IO(exception.message))
                }
            }
        }
        return result
    }
}


