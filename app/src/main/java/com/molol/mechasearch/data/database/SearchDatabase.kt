package com.molol.mechasearch.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.molol.mechasearch.data.database.dao.ProductEntityDao
import com.molol.mechasearch.data.database.dao.ResultEntityDao
import com.molol.mechasearch.data.database.dao.SearchEntityDao
import com.molol.mechasearch.data.database.model.ProductEntity
import com.molol.mechasearch.data.database.model.ResultEntity
import com.molol.mechasearch.data.database.model.SearchEntity

@Database(entities = [SearchEntity::class, ResultEntity::class, ProductEntity::class], version = 1)
abstract class SearchDatabase : RoomDatabase() {

    abstract fun searchEntityDao(): SearchEntityDao
    abstract fun productEntityDao(): ProductEntityDao
    abstract fun resultEntityDao(): ResultEntityDao

    companion object {
        fun getDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                SearchDatabase::class.java,
                "search_database"
            )
                .build()
    }
}