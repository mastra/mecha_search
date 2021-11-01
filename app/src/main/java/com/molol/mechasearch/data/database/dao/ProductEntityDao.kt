package com.molol.mechasearch.data.database.dao

import androidx.room.*
import com.molol.mechasearch.data.database.model.ProductEntity
import com.molol.mechasearch.data.database.model.ProductUpdate
import com.molol.mechasearch.data.database.model.ResultEntity

@Dao
interface ProductEntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductEntity)

    @Query("SELECT * from product_table WHERE id = :id")
    suspend fun select(id: String): ProductEntity?

    @Update(entity = ProductEntity::class)
    suspend fun update(productUpdate: ProductUpdate)
}