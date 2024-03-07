package com.mergenc.joelsbookstore.android.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.mergenc.androidcore.data.LoadingStateCollector
import com.mergenc.androidcore.domain.UiError
import com.mergenc.androidcore.view.CoreFragment
import com.mergenc.androidcore.view.LoadingInterface

typealias Inflater<T> = (LayoutInflater, ViewGroup?, Boolean) -> T
abstract class BaseFragment<VM, VB : ViewBinding>(
    private val inflater: Inflater<VB>,
) : CoreFragment<VM>() where VM : ViewModel, VM : LoadingStateCollector {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override val loadingInterface: LoadingInterface
        get() = (requireActivity() as MainActivity).loadingDialog
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = this.inflater.invoke(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun handleNetworkError(
        error: UiError<ApiErrorModel>,
        buttonText: String? = null,
        dialogMessageText: String? = null
    ) {

    }

}