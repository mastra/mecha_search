package com.molol.mechasearch.repository

import com.molol.mechasearch.api.model.SearchResult
import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.domain.model.ItemList

interface ItemRepository {
    suspend fun search(query: String): ItemList
    suspend fun detail(id: String): Item
}