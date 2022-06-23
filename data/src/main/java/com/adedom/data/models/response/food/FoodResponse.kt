package com.adedom.data.models.response.food

import kotlinx.serialization.Serializable

@Serializable
data class FoodResponse(
    val version: String?,
    val status: String?,
    val result: List<Food>?,
//    var error: BaseError?,
)