package com.molol.mechasearch.ui.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.molol.mechasearch.api.ApiService
import com.molol.mechasearch.api.model.SearchResult
import com.molol.mechasearch.domain.model.ItemList
import com.molol.mechasearch.repository.ItemRepository
import com.molol.mechasearch.util.SampleItemResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchViewModel(val repository: ItemRepository) : ViewModel() {
    //val searchResult = Gson().fromJson(SampleItemResult.itemResultShort, SearchResult::class.java)
    val itemList: MutableState<ItemList> = mutableStateOf(ItemList())

    init {

        //request()
    }

    fun search(query: String) {
        viewModelScope.launch {

            //CoroutineScope(Dispatchers.IO).launch {
            var resp = repository.search(query) //"MLA1108034370")

            Log.d("DEBUG", "response ${resp.items}")//show Recyclerview
            itemList.value = resp

            //}
        }
    }
}