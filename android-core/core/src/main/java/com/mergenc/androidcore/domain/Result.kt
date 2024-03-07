package com.mergenc.androidcore.domain

sealed class Result<out T,out E> {
    class Success<T : UiModel>(val response: T?) : Result<T,Nothing>()
    class Error<E>(val error: UiError<E>) : Result<Nothing,E>()
}
