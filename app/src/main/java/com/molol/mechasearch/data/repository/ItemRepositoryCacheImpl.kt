package com.molol.mechasearch.data.repository

import coil.compose.ImagePainter
import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.domain.model.ItemList
import com.molol.mechasearch.domain.repository.ItemRepository
import com.molol.mechasearch.domain.util.GResult

class ItemRepositoryCacheImpl(
    val api: ItemRepositoryApiImpl,
    val database: ItemRepositoryDatabaseImpl
) : ItemRepository {
    override suspend fun search(query: String, offset: Int): GResult<ItemList> {
        val itemList = database.search(query, offset)
        if (itemList is GResult.Success) {
            return itemList
        }
        return api.search(query, offset).also {
            if (it is GResult.Success) {
                database.insert(query, it.data)
            }

        }
    }

    override suspend fun detail(id: String): GResult<Item> {
        val item = database.detail(id)
        if (item !is GResult.Success) {
            api.detail(id).also {
                if (it is GResult.Success) {
                    it.data.description?.let { desc ->
                        database.updateDetail(id, desc)
                    }
                    it.data.pictures_url?.let { urls ->
                        database.insertPictures(id, urls)
                    }
                }

            }
        }
        return item
    }
}