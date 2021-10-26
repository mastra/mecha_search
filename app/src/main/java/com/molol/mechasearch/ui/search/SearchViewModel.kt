package com.molol.mechasearch.ui.search

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.molol.mechasearch.api.SearchResult
import com.molol.mechasearch.util.SampleItemResult

class SearchViewModel : ViewModel() {
    val searchResult = Gson().fromJson(SampleItemResult.itemResultShort, SearchResult::class.java)

}