package com.adedom.data.providers.remote.auth

import com.adedom.data.BuildConfig
import com.adedom.data.models.request.login.LoginRequest
import com.adedom.data.models.response.BaseResponse
import com.adedom.data.models.response.token.TokenResponse
import com.adedom.data.providers.remote.DataSourceProvider
import com.adedom.data.providers.remote.DataSourceType
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class AuthRemoteDataSourceImpl(
    private val dataSourceProvider: DataSourceProvider,
) : AuthRemoteDataSource {

    override suspend fun callLogin(loginRequest: LoginRequest): BaseResponse<TokenResponse> {
        return dataSourceProvider.getHttpClient(DataSourceType.UN_AUTHORIZATION)
            .post(BuildConfig.BASE_URL + "api/auth/login") {
                contentType(ContentType.Application.Json)
                setBody(loginRequest)
            }
            .body()
    }

    override suspend fun callLogout(): BaseResponse<String> {
        return dataSourceProvider.getHttpClient(DataSourceType.AUTHORIZATION)
            .post(BuildConfig.BASE_URL + "api/auth/logout")
            .body()
    }
}