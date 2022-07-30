package com.adedom.data.models.response.user_profile

import kotlinx.serialization.Serializable

@Serializable
data class UserProfileResponse(
    val userId: String,
    val email: String,
    val name: String,
    val mobileNo: String?,
    val address: String?,
    val image: String?,
    val status: String,
    val created: String,
    val updated: String?,
)