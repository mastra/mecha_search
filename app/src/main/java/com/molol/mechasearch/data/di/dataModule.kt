package com.molol.mechasearch.data.di

import com.molol.mechasearch.data.api.ApiService
import com.molol.mechasearch.data.database.SearchDatabase
import com.molol.mechasearch.data.repository.ItemRepositoryApiImpl
import com.molol.mechasearch.domain.repository.ItemRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<ApiService> { ApiService.getService() }

    single<ItemRepository> { ItemRepositoryApiImpl(get()) }

    single<SearchDatabase> { SearchDatabase.getDatabase(androidApplication()) }
}