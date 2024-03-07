package com.mergenc.joelsbookstore.android.core

import com.google.gson.annotations.SerializedName


data class ApiErrorModel(
    val type:String,
    val title:String,
    val status:String,
    val traceId:String,
    @SerializedName("detail")
    val message:String?
)