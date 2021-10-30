package com.molol.mechasearch.data.di

import com.molol.mechasearch.data.api.ApiService
import com.molol.mechasearch.data.repository.ItemRepositoryApiImpl
import com.molol.mechasearch.domain.repository.ItemRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<ApiService> {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        retrofit.create(ApiService::class.java)
    }

    single<ItemRepository> { ItemRepositoryApiImpl(get()) }
}