package com.adedom.data.repositories.auth

import com.adedom.data.models.request.login.LoginRequest
import com.adedom.data.providers.data_store.AppDataStore
import com.adedom.data.providers.remote.auth.AuthRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthLoginRepositoryImpl(
    private val appDataStore: AppDataStore,
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthLoginRepository {

    override suspend fun callLogin(loginRequest: LoginRequest) {
        return withContext(Dispatchers.IO) {
            val loginResponse = authRemoteDataSource.callLogin(loginRequest)
            val accessToken = loginResponse.result?.accessToken.orEmpty()
            val refreshToken = loginResponse.result?.refreshToken.orEmpty()
            appDataStore.setAccessToken(accessToken)
            appDataStore.setRefreshToken(refreshToken)
        }
    }
}