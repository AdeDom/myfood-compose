package com.adedom.data.repositories.auth

import com.adedom.data.models.request.login.LoginRequest
import com.adedom.data.repositories.Resource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

class AuthLoginRepositoryImpl : AuthLoginRepository {

    override suspend fun callLogin(loginRequest: LoginRequest): Resource<Unit> {
        return coroutineScope {
            delay(2000)
            if (loginRequest.email == "dom6" && loginRequest.password == "dom6") {
                Resource.Success(Unit)
            } else {
                val errorMessage = "Email or password incorrect"
                Resource.Error(errorMessage)
            }
        }
    }
}