package com.mergenc.androidcore.domain

import kotlinx.coroutines.flow.MutableStateFlow
import com.mergenc.androidcore.data.LoadingState

interface UseCase<T:UiModel,out E> {

    suspend operator fun invoke(
        params: UseCaseParams? = null,
        loadingFlow: MutableStateFlow<LoadingState> = MutableStateFlow(LoadingState()),
        showLoading: Boolean = true
    ): Result<T,E>
}

interface UseCaseParams
interface UiModel