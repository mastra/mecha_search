package com.molol.mechasearch

import com.google.gson.Gson
import com.molol.mechasearch.data.api.model.Description
import com.molol.mechasearch.data.api.model.ItemResult
import com.molol.mechasearch.data.api.model.SearchResult
import com.molol.mechasearch.data.api.util.toModel
import com.molol.mechasearch.util.SampleItemResult
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ItemMapperUnitTest {
    @Test
    fun itemJsonConvert() {
        val result1 = Gson().fromJson(SampleItemResult.item1, ItemResult::class.java)
        val item1 = result1.toModel()

        assertEquals(result1.id, item1.id)
        assertEquals(result1.title, item1.title)
        assertEquals(result1.thumbnail, item1.thumbnail)
    }

    @Test
    fun searchResultJsonConvert() {
        val results = Gson().fromJson(SampleItemResult.itemResultShort, SearchResult::class.java)
        assertEquals("trek", results.query)
        assertEquals(399, results.paging?.total)
        assertEquals("MLA1108034370", results?.results?.first()?.id)
    }

    @Test
    fun descriptionConvert() {
        val itemDescription =
            Gson().fromJson(SampleItemResult.itemDescription, Description::class.java)

        val desc = itemDescription.toModel()
        assertTrue(desc.startsWith("TREK MARLIN 7 27,5"))
    }

}