package com.molol.mechasearch.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "free_shipping") val free_shipping: Boolean,
    @ColumnInfo(name = "description") var description: String?
)

data class ProductUpdate(val id: String, val description: String)