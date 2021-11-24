package com.molol.mechasearch.domain.repository

import com.molol.mechasearch.data.api.model.SearchResult
import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.domain.model.ItemList
import com.molol.mechasearch.domain.util.GResult

interface ItemRepository {
    suspend fun search(query: String, offset: Int = 0): GResult<ItemList>
    suspend fun detail(id: String): GResult<Item>
}