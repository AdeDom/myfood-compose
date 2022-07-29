package com.adedom.data.models.request.token

import kotlinx.serialization.Serializable

@Serializable
data class TokenRequest(
    val accessToken: String?,
    val refreshToken: String?,
)