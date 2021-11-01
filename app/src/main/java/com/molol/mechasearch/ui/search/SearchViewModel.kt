package com.molol.mechasearch.ui.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.domain.model.ItemList
import com.molol.mechasearch.domain.repository.ItemRepository
import com.molol.mechasearch.usecase.SearchUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(val searchUseCase: SearchUseCase) : ViewModel() {
    //val searchResult = Gson().fromJson(SampleItemResult.itemResultShort, SearchResult::class.java)
    val itemList: MutableState<ItemList> = mutableStateOf(ItemList())
    val showLoading: MutableState<Boolean> = mutableStateOf(false)
    val query: MutableState<String> = mutableStateOf("")

    fun search(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            showLoading.value = true
            var resp = searchUseCase(query) //"MLA1108034370")
            showLoading.value = false
            Log.d("DEBUG", "response ${resp.items}")
            itemList.value = resp
        }
    }

    fun loadMore(offset: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            showLoading.value = true
            var resp = searchUseCase(query.value, offset)
            showLoading.value = false
            Log.d("DEBUG", "response ${resp.items}")
            itemList.value
            val all = mutableListOf<Item>()
            itemList.value.items?.let {
                all.addAll(it)
            }
            resp.items?.let {
                all.addAll(it)
            }
        }
    }
}