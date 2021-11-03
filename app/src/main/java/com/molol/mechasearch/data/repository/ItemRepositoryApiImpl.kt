package com.molol.mechasearch.data.repository

import com.molol.mechasearch.data.api.ApiService
import com.molol.mechasearch.data.api.model.ItemDetail
import com.molol.mechasearch.data.api.util.toModel
import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.domain.model.ItemList
import com.molol.mechasearch.domain.repository.ItemRepository
import kotlinx.coroutines.*
import kotlin.system.*

class ItemRepositoryApiImpl(
    val apiService: ApiService
) : ItemRepository {
    override suspend fun search(query: String, offset: Int) =
        try {
            apiService.search(query, offset).toModel()
        } catch (e: Exception) {
            ItemList()
        }


    override suspend fun detail(id: String) =
        coroutineScope {
            try {
                val product = async { apiService.detail(id) }
                val desc = async { apiService.description(id) }
                product.await().toModel().apply {
                    description = desc.await().toModel()
                }
            } catch (e: Exception) {
                Item()
            }
        }

}


