package com.molol.mechasearch.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_table")
data class SearchEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "query") val query: String,
    @ColumnInfo(name = "total") val total: Int
)
