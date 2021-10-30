package com.molol.mechasearch.ui.di

import com.molol.mechasearch.data.api.ApiService
import com.molol.mechasearch.data.api.ApiService.Companion.BASE_URL
import com.molol.mechasearch.domain.repository.ItemRepository
import com.molol.mechasearch.data.repository.ItemRepositoryApiImpl
import com.molol.mechasearch.ui.product.ProductViewModel
import com.molol.mechasearch.ui.search.SearchViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    viewModel { SearchViewModel(get()) }
    viewModel { ProductViewModel(get()) }
}