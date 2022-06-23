package com.adedom.data.models.response.category

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val categoryId: Int?,
    val categoryName: String?,
    val image: String?,
    val categoryTypeName: String?,
    val created: String?,
//    val updated: String?,
)