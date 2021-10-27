package com.molol.mechasearch.repository

import com.molol.mechasearch.api.ApiService
import com.molol.mechasearch.api.model.SearchResult
import com.molol.mechasearch.api.util.toModel
import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.domain.model.ItemList

class ItemRepositoryApiImpl(
    val apiService: ApiService
) : ItemRepository {
    override suspend fun search(query: String): ItemList {
        val response = apiService.search(query)
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