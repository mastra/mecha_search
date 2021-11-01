package com.molol.mechasearch.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.molol.mechasearch.data.database.model.ProductEntity
import com.molol.mechasearch.data.database.model.SearchEntity


@Dao
interface SearchEntityDao {
        @Query("SELECT * FROM search_table")
        suspend fun getAll(): List<SearchEntity>

        @Query("SELECT * FROM search_table WHERE query LIKE :query")
        suspend fun getSearch(query: String): SearchEntity?

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(search: SearchEntity): Long

        @Query(
                "SELECT product_table.* FROM search_table, result_table, product_table " +
                        "WHERE query LIKE :query AND search_table.id = result_table.search_id " +
                        "AND result_table.product_id = product_table.id"
        )
        suspend fun searchProducts(query: String): List<ProductEntity>

}

