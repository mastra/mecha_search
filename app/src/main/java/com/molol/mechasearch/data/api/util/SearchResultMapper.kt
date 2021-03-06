package com.molol.mechasearch.data.api.util

import com.molol.mechasearch.data.api.model.SearchResult
import com.molol.mechasearch.domain.model.ItemList

//interface Mapper  {
fun SearchResult.toModel(): ItemList {
    val itemList = ItemList(paging?.total ?: 0,
        this.results?.map { it.toModel() })
    return itemList
}
//}