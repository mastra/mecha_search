package com.molol.mechasearch.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.molol.mechasearch.data.database.SearchDatabase
import com.molol.mechasearch.data.database.dao.ProductEntityDao
import com.molol.mechasearch.data.database.model.ProductEntity
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ProductDaoTest {

    private lateinit var productDao: ProductEntityDao
    private lateinit var searchDatabase: SearchDatabase

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()

        searchDatabase = Room.inMemoryDatabaseBuilder(context, SearchDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        productDao = searchDatabase.productEntityDao()
    }

    @After
    @Throws(IOException::class)
    fun close() {
        searchDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun `insert_one_and_get`() = runBlocking {
        val product = ProductEntity(
            "MLA1", "Trek 8800", "image.jpg", 2000,
            true, "description"
        )

        productDao.insert(product)

        val actual = productDao.select(product.id)
        Assert.assertEquals(product.title, actual.title)
        Assert.assertEquals(product.price, actual.price)
    }
}