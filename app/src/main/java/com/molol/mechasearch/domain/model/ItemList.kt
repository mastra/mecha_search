package com.molol.mechasearch.domain.model

data class ItemList(
    val total: Int = 0,
    val items: List<Item>? = null
)