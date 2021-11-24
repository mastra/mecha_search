package com.molol.mechasearch.domain.util

// from https://github.com/android/architecture-samples
//
sealed class GResult<out R> {

    data class Success<out T>(val data: T) : GResult<T>()
    data class Error(val exception: Exception) : GResult<Nothing>()
    object Loading : GResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}


val GResult<*>.succeeded
    get() = this is GResult.Success && data != null