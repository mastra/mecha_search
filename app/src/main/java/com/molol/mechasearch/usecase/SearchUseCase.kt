package com.molol.mechasearch.usecase

import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.domain.model.ItemList
import com.molol.mechasearch.domain.repository.ItemRepository
import com.molol.mechasearch.domain.util.GResult

class SearchUseCase(private val repository: ItemRepository) {
    suspend operator fun invoke(query: String, offset: Int = 0): GResult<ItemList> =
        repository.search(query, offset)

}