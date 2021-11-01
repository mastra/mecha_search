package com.molol.mechasearch.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.molol.mechasearch.data.database.SearchDatabase
import com.molol.mechasearch.data.database.dao.ProductEntityDao
import com.molol.mechasearch.data.database.dao.ResultEntityDao
import com.molol.mechasearch.data.database.dao.SearchEntityDao
import com.molol.mechasearch.data.database.model.ProductEntity
import com.molol.mechasearch.data.database.model.ResultEntity
import com.molol.mechasearch.data.database.model.SearchEntity
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SearchDaoTest {

    private lateinit var searchDao: SearchEntityDao
    private lateinit var productDao: ProductEntityDao
    private lateinit var resultDao: ResultEntityDao
    private lateinit var searchDatabase: SearchDatabase

    @Before
    fun setup() {
        val context: Context = getApplicationContext()

        searchDatabase = Room.inMemoryDatabaseBuilder(context, SearchDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        searchDao = searchDatabase.searchEntityDao()
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
        val search = SearchEntity(1, "trek", 30)

        searchDao.insert(search = search)

        val actual = searchDao.getAll().first()
        assertEquals(search.query, actual.query)
        assertEquals(search.total, actual.total)
    }

    @Test
    @Throws(Exception::class)
    fun `insert_many_get_one`() = runBlocking {
        val search1 = SearchEntity(1, "trek", 30)
        val search2 = SearchEntity(2, "giant", 70)

        searchDao.insert(search = search2)
        searchDao.insert(search = search1)

        val actual = searchDao.getSearch("trek")
        assertEquals(search1.query, actual?.query)
        assertEquals(search1.total, actual?.total)
    }

    @Test
    @Throws(Exception::class)
    fun `insert_complete_search_product_and_get`() = runBlocking {
        val product1 = ProductEntity(
            "MLA1", "Trek 1", "image1.jpg", 1000,
            true, "description1"
        )
        val product2 = ProductEntity(
            "MLA2", "Trek 2", "image2.jpg", 2000,
            true, "description2"
        )
        val product3 = ProductEntity(
            "MLA3", "Giant", "image2.jpg", 2000,
            true, "description2"
        )
        productDao.insert(product1)
        productDao.insert(product2)
        productDao.insert(product3)

        val search = SearchEntity(1, "trek", 2)
        searchDao.insert(search)

        val result1 = ResultEntity(1, product1.id)
        val result2 = ResultEntity(1, product2.id)
        resultDao.insert(result1)
        resultDao.insert(result2)

        val actual = searchDao.searchProducts("trek")
        assertEquals(2, actual.size)
        assertEquals(product1.id, actual.first { it.id == product1.id }.id)
        assertEquals(product2.id, actual.first { it.id == product2.id }.id)
    }

    @Test
    @Throws(Exception::class)
    fun `get-non-existent`() = runBlocking {
        val actual = searchDao.getSearch("caloi")
        assertNull(actual)

    }
}