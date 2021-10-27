package com.molol.mechasearch.di

import com.molol.mechasearch.api.ApiService
import com.molol.mechasearch.api.ApiService.Companion.BASE_URL
import com.molol.mechasearch.repository.ItemRepository
import com.molol.mechasearch.repository.ItemRepositoryApiImpl
import com.molol.mechasearch.ui.detail.DetailViewModel
import com.molol.mechasearch.ui.search.SearchViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel() }

    single<ApiService> {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        retrofit.create(ApiService::class.java)
    }

    single<ItemRepository> { ItemRepositoryApiImpl(get()) }
}