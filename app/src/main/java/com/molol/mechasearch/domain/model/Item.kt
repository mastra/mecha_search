package com.molol.mechasearch.domain.model

import com.molol.mechasearch.api.Description

data class Item(
    val id: String? = null,
    val title: String? = null,
    val thumbnail: String? = null,
    val price: Int? = null,
    val free_shipping: Boolean = false,
    val pictures_url: List<String>? = null,
    var description: String? = null
)

object TestItems {
    val item1 = Item(
        "MLA1108034370",
        "Trek Marilin 7 R29 - 50k - Solo Calle - Nueva",
        "http://http2.mlstatic.com/D_822157-MLA47864216339_102021-O.jpg",
        260000
    )
    val item2 = Item(
        "MLA1107564062",
        "Trek Marlin 4 Xl 2022 ",
        "http://http2.mlstatic.com/D_851644-MLA47811515043_102021-O.jpg",
        1000
    )
    val item3 = Item(
        "MLA919167439",
        "Bicicleta Trek Marlin 7 2020   27,5  ",
        "http://http2.mlstatic.com/D_982427-MLA45813369584_052021-O.jpg",
        235223
    )
}