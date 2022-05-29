package com.adedom.data.models.error

import kotlinx.serialization.Serializable

@Serializable
data class BaseError(
    val code: String? = null,
    val message: String? = null,
)