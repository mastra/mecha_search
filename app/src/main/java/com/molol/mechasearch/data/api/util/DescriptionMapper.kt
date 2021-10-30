package com.molol.mechasearch.data.api.util

import com.molol.mechasearch.data.api.model.Description
import com.molol.mechasearch.domain.util.ModelMapper

//class DescriptionMapper : ModelMapper<Description, String> {
fun Description.toModel() = plain_text ?: ""

//}