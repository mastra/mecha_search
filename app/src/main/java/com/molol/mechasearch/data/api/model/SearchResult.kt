package com.molol.mechasearch.data.api.model

class SearchResult {
    var site_id: String? = null
    var query: String? = null
    var paging: Paging? = null
    var results: List<ItemResult>? = null
}