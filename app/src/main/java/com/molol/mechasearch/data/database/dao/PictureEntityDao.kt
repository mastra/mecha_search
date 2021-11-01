package com.molol.mechasearch.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.molol.mechasearch.data.database.model.PictureEntity

@Dao
interface PictureEntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pictures: List<PictureEntity>)

    @Query("SELECT * from picture_table WHERE id = :id")
    suspend fun select(id: String): PictureEntity?

    @Query("SELECT * from picture_table WHERE product_id = :product_id")
    suspend fun selectByProduct(product_id: String): List<PictureEntity>

}