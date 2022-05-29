package com.adedom.data.repositories.auth

import com.adedom.data.models.request.login.LoginRequest
import com.adedom.data.repositories.Resource

interface AuthLoginRepository {
    suspend fun callLogin(loginRequest: LoginRequest): Resource<Unit>
}