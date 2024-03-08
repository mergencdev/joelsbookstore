package com.mergenc.joelsbookstore.android.features.list.domain

import com.mergenc.androidcore.domain.UiError
import com.mergenc.androidcore.domain.UiModel
import com.mergenc.joelsbookstore.android.core.ApiErrorModel
import com.mergenc.joelsbookstore.android.features.list.data.dto.Results

/**
 * Created by Mehmet Emin Ergenc on 3/7/2024
 */

data class ListUiModel(val results: Results) : UiModel

sealed class ListApiState {
    data class Success(val uiModel: ListUiModel?) : ListApiState()
    data class Error(val error: UiError<ApiErrorModel>) : ListApiState()
    object Initial : ListApiState()
}