package com.mergenc.joelsbookstore.android.features.list.domain

import com.mergenc.androidcore.data.LoadingState
import com.mergenc.androidcore.domain.UseCase
import com.mergenc.androidcore.domain.UseCaseParams
import com.mergenc.joelsbookstore.android.core.ApiErrorModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import com.mergenc.androidcore.domain.Result

/**
 * Created by Mehmet Emin Ergenc on 3/7/2024
 */

class GetListUseCase @Inject constructor(
    private val listRepository: ListRepository
) : UseCase<ListUiModel, ApiErrorModel> {
    override suspend fun invoke(
        params: UseCaseParams?,
        loadingFlow: MutableStateFlow<LoadingState>,
        showLoading: Boolean
    ): Result<ListUiModel, ApiErrorModel> {
        return listRepository.getList(loadingFlow, showLoading)
    }
}