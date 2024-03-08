package com.mergenc.joelsbookstore.android.features.detail.presentation

import androidx.lifecycle.ViewModel
import com.mergenc.androidcore.data.LoadingState
import com.mergenc.androidcore.data.LoadingStateCollector
import com.mergenc.joelsbookstore.android.features.list.domain.GetListUseCase
import com.mergenc.joelsbookstore.android.features.list.domain.ListApiState
import com.mergenc.joelsbookstore.android.features.list.presentation.ListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Created by Mehmet Emin Ergenc on 3/8/2024
 */

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel(), LoadingStateCollector {

    override val loadingStateFlow = MutableStateFlow(LoadingState())
}