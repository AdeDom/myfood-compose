package com.adedom.data.repositories.auth

import com.adedom.data.models.request.login.LoginRequest
import com.adedom.data.models.response.token.TokenResponse
import com.adedom.data.providers.data_store.AppDataStore
import com.adedom.data.providers.remote.auth.AuthRemoteDataSource
import com.adedom.data.utils.AuthRole
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthLoginRepositoryImpl(
    private val appDataStore: AppDataStore,
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthLoginRepository {

    override suspend fun callLogin(loginRequest: LoginRequest): TokenResponse? {
        return withContext(Dispatchers.IO) {
            val loginResponse = authRemoteDataSource.callLogin(loginRequest)
            loginResponse.result
        }
    }

    override suspend fun saveToken(accessToken: String, refreshToken: String) {
        return withContext(Dispatchers.IO) {
            appDataStore.setAccessToken(accessToken)
            appDataStore.setRefreshToken(refreshToken)
        }
    }

    override suspend fun saveAuthRole() {
        return withContext(Dispatchers.IO) {
            val authRole = AuthRole.Auth
            appDataStore.setAuthRole(authRole)
        }
    }
}