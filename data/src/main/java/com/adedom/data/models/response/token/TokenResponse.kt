package com.adedom.data.models.response.token

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val accessToken: String?,
    val refreshToken: String?,
)