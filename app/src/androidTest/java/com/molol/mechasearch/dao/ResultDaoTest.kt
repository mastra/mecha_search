package com.molol.mechasearch.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.molol.mechasearch.data.database.SearchDatabase
import com.molol.mechasearch.data.database.dao.ProductEntityDao
import com.molol.mechasearch.data.database.dao.ResultEntityDao
import com.molol.mechasearch.data.database.model.ProductEntity
import com.molol.mechasearch.data.database.model.ResultEntity
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ResultDaoTest {

    private lateinit var productDao: ProductEntityDao
    private lateinit var resultDao: ResultEntityDao
    private lateinit var searchDatabase: SearchDatabase

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()

        searchDatabase = Room.inMemoryDatabaseBuilder(context, SearchDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        productDao = searchDatabase.productEntityDao()
        resultDao = searchDatabase.resultEntityDao()
    }

    @After
    @Throws(IOException::class)
    fun close() {
        searchDatabase.close()
    }


    @Test
    @Throws(Exception::class)
    fun `insert_one_and_get`() = runBlocking {
        val result = ResultEntity(1, "MLA1")

        resultDao.insert(result)

        val actual = resultDao.select(result.search_id, result.product_id)
        Assert.assertEquals(result.product_id, actual.product_id)
    }


}