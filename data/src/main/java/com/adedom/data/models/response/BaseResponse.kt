package com.adedom.data.models.response

import com.adedom.data.models.error.BaseError
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    var version: String = "1.0",
    var status: String = "error",
    var result: T? = null,
    var error: BaseError? = null,
)