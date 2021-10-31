package com.molol.mechasearch.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.molol.mechasearch.data.database.model.ResultEntity
import com.molol.mechasearch.data.database.model.SearchEntity

@Dao
interface ResultEntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(search: ResultEntity)

    @Query("SELECT * FROM result_table WHERE search_id=:search_id AND product_id=:product_id")
    suspend fun select(search_id: Int, product_id: String): ResultEntity
}