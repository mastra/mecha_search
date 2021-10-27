package com.molol.mechasearch.api.util

import com.molol.mechasearch.api.model.ItemDetail
import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.domain.util.ModelMapper

//class ItemDetailMapper : ModelMapper<ItemDetail, Item> {
fun ItemDetail.toModel() = Item(
    id,
    title,
    thumbnail,
    price?.toInt(),
    shipping?.free_shipping ?: false,
    pictures?.map { it.url ?: "" }
)

//}