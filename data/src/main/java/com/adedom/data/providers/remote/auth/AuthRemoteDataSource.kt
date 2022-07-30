package com.adedom.data.providers.remote.auth

import com.adedom.data.models.request.login.LoginRequest
import com.adedom.data.models.response.BaseResponse
import com.adedom.data.models.response.token.TokenResponse

interface AuthRemoteDataSource {

    suspend fun callLogin(loginRequest: LoginRequest): BaseResponse<TokenResponse>

    suspend fun callLogout(): BaseResponse<String>
}