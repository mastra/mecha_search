package com.molol.mechasearch.data.database.mapper

import com.molol.mechasearch.data.database.model.ProductEntity
import com.molol.mechasearch.domain.model.Item


fun ProductEntity.toModel() =
    Item(id, title, thumbnail, price, free_shipping, null, description)
