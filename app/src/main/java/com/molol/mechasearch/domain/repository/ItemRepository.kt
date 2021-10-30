package com.molol.mechasearch.domain.repository

import com.molol.mechasearch.data.api.model.SearchResult
import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.domain.model.ItemList

interface ItemRepository {
    suspend fun search(query: String, offset: Int = 0): ItemList
    suspend fun detail(id: String): Item
}