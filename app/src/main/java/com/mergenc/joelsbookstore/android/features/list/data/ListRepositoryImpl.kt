package com.mergenc.joelsbookstore.android.features.list.data

import com.mergenc.androidcore.BuildConfig
import com.mergenc.androidcore.data.ApiExecutor
import com.mergenc.androidcore.data.LoadingState
import com.mergenc.androidcore.data.remote.ApiResult
import com.mergenc.joelsbookstore.android.features.list.domain.ListRepository
import com.mergenc.joelsbookstore.android.features.list.domain.ListUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import com.mergenc.androidcore.domain.Result
import com.mergenc.androidcore.domain.parseError
import com.mergenc.joelsbookstore.android.core.ApiErrorModel
import com.mergenc.joelsbookstore.android.features.list.data.dto.toDomain
import javax.inject.Inject

/**
 * Created by Mehmet Emin Ergenc on 3/7/2024
 */

class ListRepositoryImpl @Inject constructor(
    private val listApi: ListApi,
) : ListRepository, ApiExecutor {
    override suspend fun getList(
        loadingFlow: MutableStateFlow<LoadingState>,
        showLoading: Boolean
    ): Result<ListUiModel, ApiErrorModel> {
        val apiResult = execute(
            call = {
                listApi.getList(
                    date = "2024-03-07",
                    list = "hardcover-fiction",
                    apiKey = BuildConfig.API_KEY
                )
            }, showLoading,
            loadingFlow
        )
        return when (apiResult) {
            is ApiResult.Success -> {
                Result.Success(apiResult.response?.toDomain())
            }

            is ApiResult.Error -> {
                Result.Error(parseError<ApiErrorModel>(apiResult).error)
            }
        }
    }
}