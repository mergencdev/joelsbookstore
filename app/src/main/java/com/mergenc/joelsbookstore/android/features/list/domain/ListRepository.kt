package com.mergenc.joelsbookstore.android.features.list.domain

import com.mergenc.androidcore.data.LoadingState
import com.mergenc.joelsbookstore.android.core.ApiErrorModel
import kotlinx.coroutines.flow.MutableStateFlow
import com.mergenc.androidcore.domain.Result

/**
 * Created by Mehmet Emin Ergenc on 3/7/2024
 */

interface ListRepository {
    suspend fun getList(
        loadingFlow: MutableStateFlow<LoadingState>,
        showLoading: Boolean
    ): Result<ListUiModel, ApiErrorModel>
}