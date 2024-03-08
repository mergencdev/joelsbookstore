package com.mergenc.joelsbookstore.android.features.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mergenc.androidcore.data.LoadingState
import com.mergenc.androidcore.data.LoadingStateCollector
import com.mergenc.joelsbookstore.android.features.list.domain.GetListUseCase
import com.mergenc.joelsbookstore.android.features.list.domain.ListApiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.mergenc.androidcore.domain.Result
import com.mergenc.joelsbookstore.android.features.list.data.dto.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Mehmet Emin Ergenc on 3/7/2024
 */

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getListUseCase: GetListUseCase
) : ViewModel(), LoadingStateCollector {

    override val loadingStateFlow = MutableStateFlow(LoadingState())

    private var _pageStateFlow =
        MutableStateFlow(PageState())
    val pageStateFlow: StateFlow<PageState> = _pageStateFlow.asStateFlow()

    fun getList() {
        viewModelScope.launch {
            val result = getListUseCase.invoke(null, loadingStateFlow, true)
            when (result) {
                is Result.Success -> {
                    _pageStateFlow.update {
                        val homePageApiState = ListApiState.Success(result.response)
                        it.copy(
                            pageState = PageEvent.LIST_RESPONSE_RECEIVED,
                            homePageApiState = homePageApiState
                        )
                    }
                }

                is Result.Error -> {
                    _pageStateFlow.update {
                        val homePageApiState = ListApiState.Error(result.error)
                        it.copy(
                            pageState = PageEvent.LIST_RESPONSE_RECEIVED,
                            homePageApiState = homePageApiState
                        )
                    }
                }
            }
        }
    }

    enum class PageEvent {
        INITIAL,
        LIST_RESPONSE_RECEIVED,
    }

    data class PageState(
        var pageState: PageEvent = PageEvent.INITIAL,
        val homePageApiState: ListApiState = ListApiState.Initial
    )
}