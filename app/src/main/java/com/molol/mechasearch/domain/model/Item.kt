package com.molol.mechasearch.domain.model

data class Item(
    val id: String = "",
    val title: String = "",
    val thumbnail: String = "",
    val price: Int = 0,
    val free_shipping: Boolean = false,
    val pictures_url: List<String>? = null,
    var description: String? = null
)
