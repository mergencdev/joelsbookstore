package com.mergenc.androidcore.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import java.util.concurrent.atomic.AtomicInteger

abstract class CoreLoadingDialog(context: Context) : Dialog(context),LoadingInterface {

    private var loadingCount = AtomicInteger(0)

    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(layoutId)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)
    }

    override fun show() {
        if (1 == loadingCount.incrementAndGet()) {
            super.show()
        }
    }

    override fun hide() {
        if (loadingCount.decrementAndGet().isNotGreaterThanZero()) {
            dismiss()
        }
    }

    private fun Int.isNotGreaterThanZero(): Boolean {
        return this <= 0
    }
}