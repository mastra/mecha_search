package com.molol.mechasearch.data.repository

import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.domain.model.ItemList
import com.molol.mechasearch.domain.repository.ItemRepository

class ItemRepositoryCacheImpl(
    val api: ItemRepositoryApiImpl,
    val database: ItemRepositoryDatabaseImpl
) : ItemRepository {
    override suspend fun search(query: String, offset: Int): ItemList {
        val itemList = database.search(query, offset)
        if (itemList.total > 0) {
            return itemList
        }
        return api.search(query, offset).also {
            database.insert(query, it)
        }
    }

    override suspend fun detail(id: String): Item {
        val item = database.detail(id)
        if (item.id != "" && item.description == null) {
            return api.detail(id).also {
                it.description?.let { desc ->
                    database.updateDetail(id, desc)
                }
                it.pictures_url?.let { urls ->
                    database.insertPictures(id, urls)
                }

            }
        }
        return item
    }
}