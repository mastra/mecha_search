package com.molol.mechasearch.data.api

import com.molol.mechasearch.data.api.model.Description
import com.molol.mechasearch.data.api.model.ItemDetail
import com.molol.mechasearch.data.api.model.SearchResult
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor


interface ApiService {
    suspend fun search(q: String, offset: Int = 0): SearchResult
    suspend fun detail(id: String): ItemDetail
    suspend fun description(id: String): Description

    companion object {
        const val BASE_URL = "https://api.mercadolibre.com/"
    }
}