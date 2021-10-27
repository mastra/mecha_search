package com.molol.mechasearch.api.util

import com.molol.mechasearch.api.model.ItemDetail
import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.domain.util.ModelMapper

class ItemDetailMapper : ModelMapper<ItemDetail, Item> {
    override fun toModel(apiModel: ItemDetail) = Item(
        apiModel.id,
        apiModel.title,
        apiModel.thumbnail,
        apiModel.price?.toInt(),
        apiModel.shipping?.free_shipping ?: false,
        apiModel.pictures?.map { it.url ?: "" }
    )

}