package com.molol.mechasearch.api.util

import com.molol.mechasearch.api.model.Description
import com.molol.mechasearch.domain.util.ModelMapper

//class DescriptionMapper : ModelMapper<Description, String> {
fun Description.toModel() = plain_text ?: ""

//}