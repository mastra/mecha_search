package com.molol.mechasearch.data.api.util

import com.molol.mechasearch.data.api.model.ItemResult
import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.domain.util.ModelMapper

//interface ItemResultMapper  {
fun ItemResult.toModel(): Item {
    return Item(
        id,
        title,
        thumbnail,
        price?.toInt(),
        shipping?.free_shipping ?: false
    )
}


//}