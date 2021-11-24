package com.molol.mechasearch.data.repository

import com.molol.mechasearch.data.database.SearchDatabase
import com.molol.mechasearch.data.database.mapper.toModel
import com.molol.mechasearch.data.database.model.*
import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.domain.model.ItemList
import com.molol.mechasearch.domain.repository.ItemRepository
import com.molol.mechasearch.domain.util.GResult
import java.lang.Exception

class ItemRepositoryDatabaseImpl(val database: SearchDatabase) : ItemRepository {

    override suspend fun search(query: String, offset: Int): GResult<ItemList> {
        val searchDao = database.searchEntityDao()
        searchDao.getSearch(query)?.let {
            val products = searchDao.searchProducts(query)
            return GResult.Success(ItemList(it.total, products.map { it.toModel() }))
        }
        return GResult.Error(Exception("No Data found"))
    }

    override suspend fun detail(id: String): GResult<Item> =
        GResult.Success(
            database.productEntityDao().select(id)?.let {
                val item = it.toModel()
                val pictures = database.pictureEntityDao().selectByProduct(it.id)
                item.copy(pictures_url = pictures.map { pic ->
                    pic.url
                })
            } ?: Item()
        )


    suspend fun insert(query: String, itemList: ItemList) {
        val resultEntityDao = database.resultEntityDao()
        val productEntityDao = database.productEntityDao()

        val search = SearchEntity(0, query, itemList.total)
        val search_id = database.searchEntityDao().insert(search = search)
        itemList.items?.forEach {
            val result = ResultEntity(search_id.toInt(), it.id)
            resultEntityDao.insert(result)
            val productEntity = ProductEntity(
                it.id,
                it.title,
                it.thumbnail,
                it.price,
                it.free_shipping,
                it.description
            )
            productEntityDao.insert(productEntity)
        }
    }

    suspend fun updateDetail(id: String, description: String) {
        database.productEntityDao().update(ProductUpdate(id, description))
    }

    suspend fun insertPictures(id: String, urls: List<String>) {
        val pictures = urls.map { PictureEntity(0, id, it) }
        database.pictureEntityDao().insert(pictures)
    }
}