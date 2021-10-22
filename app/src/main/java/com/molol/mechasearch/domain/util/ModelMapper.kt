package com.molol.mechasearch.domain.util

interface ModelMapper<ApiModel, DomainModel> {
    fun toModel(apiModel: ApiModel): DomainModel
    //fun toApi( domainModel: DomainModel) : ApiModel
}