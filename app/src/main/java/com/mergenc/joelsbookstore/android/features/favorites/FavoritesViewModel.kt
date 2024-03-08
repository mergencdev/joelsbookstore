package com.mergenc.joelsbookstore.android.features.favorites

import androidx.lifecycle.ViewModel
import com.mergenc.androidcore.data.LoadingState
import com.mergenc.androidcore.data.LoadingStateCollector
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
 * Created by Mehmet Emin Ergenc on 3/9/2024
 */

@HiltViewModel
class FavoritesViewModel @Inject constructor() : ViewModel(), LoadingStateCollector {

    override val loadingStateFlow = MutableStateFlow(LoadingState())
}