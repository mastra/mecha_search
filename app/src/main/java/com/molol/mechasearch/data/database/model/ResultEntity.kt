package com.molol.mechasearch.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "result_table", primaryKeys = ["search_id", "product_id"])
data class ResultEntity(
    @ColumnInfo(name = "search_id") val search_id: Int,
    @ColumnInfo(name = "product_id") val product_id: String
)