package com.mergenc.androidcore.data

import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Random

interface LoadingStateCollector {
    val loadingStateFlow: MutableStateFlow<LoadingState>
}

data class LoadingState(
    var show: Visibility = Visibility.DEFAULT,
    val uniqueId: Int = Random().nextInt()
)

enum class Visibility {
    SHOW, HIDE, DEFAULT
}