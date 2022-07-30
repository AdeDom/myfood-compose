package com.adedom.domain.models.user_profile

data class UserProfileModel(
    val userId: String,
    val email: String,
    val name: String,
    val mobileNo: String,
    val address: String,
    val image: String,
)