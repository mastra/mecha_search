package com.molol.mechasearch.data.api.ktor

import android.util.Log
import com.molol.mechasearch.data.api.ApiService
import com.molol.mechasearch.data.api.ApiService.Companion.BASE_URL
import com.molol.mechasearch.data.api.model.Description
import com.molol.mechasearch.data.api.model.ItemDetail
import com.molol.mechasearch.data.api.model.SearchResult
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.get
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

class ApiKtorService(private val client: HttpClient) : ApiService {
    override suspend fun search(q: String, offset: Int): SearchResult {
        return try {
            client.get("${BASE_URL}sites/MLA/search") {
                parameter("q", q)
                parameter("offset", offset)
            }
        } catch (e: Exception) {
            //for all
            Log.d("_ERROR_", "Error: ${e.message}")
            SearchResult()
        }
    }

    override suspend fun detail(id: String): ItemDetail {
        return try {
            client.get("${BASE_URL}items/$id") {
            }
        } catch (e: Exception) {
            //for all
            Log.d("_ERROR_", "Error: ${e.message}")
            ItemDetail()
        }
    }

    override suspend fun description(id: String): Description {
        return try {
            client.get("${BASE_URL}items/$id/description") {
            }
        } catch (e: Exception) {
            //for all
            Log.d("_ERROR_", "Error: ${e.message}")
            Description()
        }
    }

    companion object {
        fun getService(): ApiKtorService {
            return ApiKtorService(client = HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = GsonSerializer()
                }
            })
        }
    }
}