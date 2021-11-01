package com.molol.mechasearch.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "picture_table")
data class PictureEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "product_id") val product_id: String,
    @ColumnInfo(name = "url") val url: String
)