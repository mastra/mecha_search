package com.molol.mechasearch.ui.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.domain.model.ItemList
import com.molol.mechasearch.domain.repository.ItemRepository
import com.molol.mechasearch.domain.util.GResult
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
            if (resp is GResult.Success) {
                showLoading.value = false
                Log.d("DEBUG", "response ${resp.data.items}")
                itemList.value = resp.data
            }
        }
    }

    fun loadMore(offset: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            showLoading.value = true
            var resp = searchUseCase(query.value, offset)
            if (resp is GResult.Success) {
                showLoading.value = false
                Log.d("DEBUG", "response ${resp.data.items}")
                itemList.value
                val all = mutableListOf<Item>()
                itemList.value.items?.let {
                    all.addAll(it)
                }
                resp.data.items?.let {
                    all.addAll(it)
                }
            }
        }
    }
}