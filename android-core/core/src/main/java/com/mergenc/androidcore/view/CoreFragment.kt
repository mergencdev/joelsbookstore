package com.mergenc.androidcore.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.mergenc.androidcore.data.LoadingState
import com.mergenc.androidcore.data.LoadingStateCollector
import com.mergenc.androidcore.data.Visibility

abstract class CoreFragment<VM> : Fragment() where VM : ViewModel, VM : LoadingStateCollector {

    abstract val viewModel: VM

    abstract val loadingInterface: LoadingInterface

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadingStateFlow.emit(LoadingState(Visibility.DEFAULT))
            viewModel.loadingStateFlow.collect {
                if (it.show == Visibility.SHOW) {
                    loadingInterface.show()
                } else if (it.show == Visibility.HIDE) {
                    loadingInterface.hide()
                }

            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    fun <T> collectPageState(pageState: StateFlow<T>, block: (T) -> Unit){
        viewLifecycleOwner.lifecycleScope.launch {
            pageState.collect { state ->
                block(state)
            }
        }
    }
}