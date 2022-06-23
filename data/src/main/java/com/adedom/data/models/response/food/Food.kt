package com.adedom.data.models.response.food

import kotlinx.serialization.Serializable

@Serializable
data class Food(
    val foodId: Int?,
    val foodName: String?,
//    val alias: String?,
    val image: String?,
    val price: Double?,
    val description: String?,
//    val favorite: Int?,
//    val ratingScore: Double?,
//    val ratingScoreCount: String?,
    val categoryId: Int?,
    val status: String?,
    val created: String?,
//    val updated: String?,
)