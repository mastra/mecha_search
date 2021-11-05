package com.molol.mechasearch.data.api.retrofit

import com.molol.mechasearch.data.api.ApiService
import com.molol.mechasearch.data.api.ApiService.Companion.BASE_URL
import com.molol.mechasearch.data.api.model.Description
import com.molol.mechasearch.data.api.model.ItemDetail
import com.molol.mechasearch.data.api.model.SearchResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiRetrofitService : ApiService {

    @GET("sites/MLA/search")
    override suspend fun search(@Query("q") q: String, @Query("offset") offset: Int): SearchResult

    @GET("items/{id}")
    override suspend fun detail(@Path("id") id: String): ItemDetail

    @GET("items/{id}/description")
    override suspend fun description(@Path("id") id: String): Description

    companion object {
        fun getService(): ApiRetrofitService {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
            return retrofit.create(ApiRetrofitService::class.java)
        }
    }
}