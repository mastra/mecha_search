package com.molol.mechasearch.api.util

import com.molol.mechasearch.api.ItemResult
import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.domain.util.ModelMapper

class ItemResultMapper : ModelMapper<ItemResult, Item> {
    override fun toModel(apiModel: ItemResult): Item {
        return Item(
            apiModel.id,
            apiModel.title,
            apiModel.thumbnail,
            apiModel.price,
            apiModel.shipping?.free_shipping ?: false
        )
    }


}