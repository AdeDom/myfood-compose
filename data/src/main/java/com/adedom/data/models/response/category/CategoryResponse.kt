package com.adedom.data.models.response.category

import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    var version: String?,
    var status: String?,
    var result: List<Category>?,
//    var error: BaseError?,
)