package com.molol.mechasearch.api.util

import com.molol.mechasearch.api.Description
import com.molol.mechasearch.domain.util.ModelMapper

class DescriptionMapper : ModelMapper<Description, String> {
    override fun toModel(apiModel: Description) = apiModel.plain_text ?: ""

}