package com.molol.mechasearch.domain.usecase

import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.domain.model.ItemList
import com.molol.mechasearch.domain.repository.ItemRepository

class SearchUseCase(private val repository: ItemRepository) {
    suspend operator fun invoke(query: String, offset: Int = 0) =
        repository.search(query, offset)

}