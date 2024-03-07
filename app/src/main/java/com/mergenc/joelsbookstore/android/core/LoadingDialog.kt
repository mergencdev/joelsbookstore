package com.mergenc.joelsbookstore.android.core

import android.content.Context
import com.mergenc.androidcore.view.CoreLoadingDialog
import com.mergenc.joelsbookstore.R

class LoadingDialog(context: Context) : CoreLoadingDialog(context) {
    override val layoutId: Int
        get() = R.layout.dialog_progress
}