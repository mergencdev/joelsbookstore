package com.mergenc.joelsbookstore.android.features.list.data.di

import com.mergenc.joelsbookstore.android.features.list.data.ListApi
import com.mergenc.joelsbookstore.android.features.list.data.ListRepositoryImpl
import com.mergenc.joelsbookstore.android.features.list.domain.ListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

/**
 * Created by Mehmet Emin Ergenc on 3/7/2024
 */

@Module
@InstallIn(ViewModelComponent::class)
class ListModule {

    @Provides
    fun provideListApiService(retrofit: Retrofit): ListApi {
        return retrofit.create(ListApi::class.java)
    }

    @Provides
    fun provideListRepository(
        listApi: ListApi,
    ): ListRepository {
        return ListRepositoryImpl(listApi)
    }
}