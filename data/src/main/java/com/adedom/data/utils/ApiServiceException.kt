package com.adedom.data.utils

import com.adedom.data.models.error.BaseError
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.IOException

class ApiServiceException(baseError: BaseError) : IOException(baseError.toString()) {

    fun toBaseError(): BaseError? {
        return message?.let { msg ->
            Json.decodeFromString<BaseError>(msg)
        }
    }
}