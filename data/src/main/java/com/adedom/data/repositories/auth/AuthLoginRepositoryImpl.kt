package com.adedom.data.repositories.auth

import com.adedom.data.models.error.BaseError
import com.adedom.data.models.request.login.LoginRequest
import com.adedom.data.providers.remote.auth.AuthRemoteDataSource
import com.adedom.data.repositories.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthLoginRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthLoginRepository {

    override suspend fun callLogin(loginRequest: LoginRequest): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val loginResponse = authRemoteDataSource.callLogin(loginRequest)
                val accessToken = loginResponse.result?.accessToken
                val refreshToken = loginResponse.result?.refreshToken

                Resource.Success(Unit)
            } catch (e: Throwable) {
                e.printStackTrace()
                val message = e.message
                val baseError = BaseError(message = message)
                Resource.Error(baseError)
            }
        }
    }
}