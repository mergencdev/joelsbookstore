package com.mergenc.joelsbookstore.android.features.list.data

import com.mergenc.joelsbookstore.android.features.list.data.dto.ListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Mehmet Emin Ergenc on 3/7/2024
 */

interface ListApi {

    @GET(END_POINT_GET_LIST)
    suspend fun getList(
        @Path("date") date: String,
        @Path("list") list: String,
        @Query("api-key") apiKey: String
    ): Response<ListResponse>

    companion object {
        const val END_POINT_GET_LIST = "lists/{date}/{list}.json"

    }
}