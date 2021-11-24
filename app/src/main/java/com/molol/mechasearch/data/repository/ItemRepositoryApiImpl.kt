package com.molol.mechasearch.data.repository

import com.molol.mechasearch.data.api.ApiService
import com.molol.mechasearch.data.api.util.toModel
import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.domain.model.ItemList
import com.molol.mechasearch.domain.repository.ItemRepository
import kotlinx.coroutines.*
import com.molol.mechasearch.domain.util.GResult

class ItemRepositoryApiImpl(
    val apiService: ApiService
) : ItemRepository {
    override suspend fun search(query: String, offset: Int): GResult<ItemList> =
        try {
            GResult.Success(apiService.search(query, offset).toModel())
        } catch (e: Exception) {
            GResult.Error(e)
        }


    override suspend fun detail(id: String) =
        coroutineScope {
            try {
                val product = async { apiService.detail(id) }
                val desc = async { apiService.description(id) }
                GResult.Success(
                    product.await().toModel().apply {
                        description = desc.await().toModel()
                    }
                )
            } catch (e: Exception) {
                GResult.Error(e)
            }
        }

}


