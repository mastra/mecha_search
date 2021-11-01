package com.molol.mechasearch.data.repository

import com.molol.mechasearch.data.api.ApiService
import com.molol.mechasearch.data.api.util.toModel
import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.domain.model.ItemList
import com.molol.mechasearch.domain.repository.ItemRepository

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
        try {
            apiService.detail(id).toModel().apply {
                description = apiService.description(id).toModel()
            }
        } catch (e: Exception) {
            Item()
        }

}