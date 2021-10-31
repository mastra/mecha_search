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

    @GET("sites/MLA/search")
    suspend fun search(@Query("q") q: String, @Query("offset") offset: Int): SearchResult

    @GET("items/{id}")
    suspend fun detail(@Path("id") id: String): ItemDetail

    @GET("items/{id}/description")
    suspend fun description(@Path("id") id: String): Description

    companion object {
        const val BASE_URL = "https://api.mercadolibre.com/"

        fun getService(): ApiService {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            val retrofit = Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}