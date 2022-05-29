package com.adedom.data.repositories

import com.adedom.data.models.error.BaseError

sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val error: BaseError) : Resource<Nothing>()
}