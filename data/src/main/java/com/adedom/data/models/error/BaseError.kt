package com.adedom.data.models.error

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class BaseError(
    val code: String? = null,
    val message: String? = null,
) {
    override fun toString(): String {
        return Json.encodeToString(this)
    }
}