package com.molol.mechasearch.data.repository

import com.molol.mechasearch.data.api.ApiService
import com.molol.mechasearch.data.api.util.toModel
import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.domain.model.ItemList
import com.molol.mechasearch.domain.repository.ItemRepository

class ItemRepositoryApiImpl(
    val apiService: ApiService
) : ItemRepository {
    override suspend fun search(query: String, offset: Int): ItemList {
        val response = apiService.search(query, offset)
        //if(response.isSuccessful) {
        //    return response.body()!!
        //}
        return response.toModel()
    }

    override suspend fun detail(id: String): Item {
        val item = apiService.detail(id).toModel()
        item.description = apiService.description(id).toModel()
        return item
    }
}