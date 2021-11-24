package com.molol.mechasearch.ui.product

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.domain.repository.ItemRepository
import com.molol.mechasearch.domain.util.GResult
import kotlinx.coroutines.launch

class ProductViewModel(
    val itemRepository: ItemRepository
) : ViewModel() {

    val detail: MutableState<Item> = mutableStateOf(Item())
    val showLoading: MutableState<Boolean> = mutableStateOf(false)

    fun getDetail(itemId: String): Int {

        Log.d("DEBUG", "get item $itemId !")
        viewModelScope.launch {
            showLoading.value = true
            val item = itemRepository.detail(itemId)
            if (item is GResult.Success) {
                detail.value = item.data
                showLoading.value = false
                Log.d(
                    "DEBUG",
                    "TITULO: ${item.data.title} \n DESC ${item.data.description}\n PIC: ${item.data.pictures_url}"
                )
            }

        }
        return 1
    }
}