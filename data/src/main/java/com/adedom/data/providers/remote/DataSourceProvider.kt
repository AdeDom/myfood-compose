package com.adedom.data.providers.remote

import com.adedom.data.BuildConfig
import com.adedom.data.providers.data_store.AppDataStore
import com.adedom.data.utils.ApiServiceException
import com.adedom.data.utils.AuthRole
import com.adedom.myfood.data.models.base.BaseError
import com.adedom.myfood.data.models.base.BaseResponse
import com.adedom.myfood.data.models.request.TokenRequest
import com.adedom.myfood.data.models.response.TokenResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class DataSourceProvider(
    private val appDataStore: AppDataStore,
) {

    fun getHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 60_000
            }

            if (BuildConfig.APP_TYPE == "develop") {
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.HEADERS
                }
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        val accessToken = appDataStore.getAccessToken().orEmpty()
                        val refreshToken = appDataStore.getRefreshToken().orEmpty()
                        BearerTokens(accessToken, refreshToken)
                    }

                    refreshTokens {
                        val tokenRequest = TokenRequest(
                            accessToken = oldTokens?.accessToken,
                            refreshToken = oldTokens?.refreshToken,
                        )
                        val tokenResponse: BaseResponse<TokenResponse> = client
                            .post("${BuildConfig.BASE_URL}api/auth/refreshToken") {
                                contentType(ContentType.Application.Json)
                                setBody(tokenRequest)
                            }
                            .body()
                        val accessToken = tokenResponse.result?.accessToken.orEmpty()
                        val refreshToken = tokenResponse.result?.refreshToken.orEmpty()
                        appDataStore.setAccessToken(accessToken)
                        appDataStore.setRefreshToken(refreshToken)
                        BearerTokens(accessToken, refreshToken)
                    }

                    sendWithoutRequest { request ->
                        request.url.host == "myfood-server.herokuapp.com"
                    }
                }
            }

            expectSuccess = true
            httpResponseValidator()
        }
    }

    private fun HttpClientConfig<CIOEngineConfig>.httpResponseValidator() {
        HttpResponseValidator {
            handleResponseExceptionWithRequest { exception, request ->
                val clientException = exception as? ClientRequestException
                    ?: return@handleResponseExceptionWithRequest
                val exceptionResponse = clientException.response
                when (exceptionResponse.status) {
                    HttpStatusCode.BadRequest -> {
                        val jsonString = exceptionResponse.bodyAsText()
                        val baseResponse = jsonString.decodeApiServiceResponseFromString()
                        val baseError = baseResponse.error ?: createBaseError()
                        throw ApiServiceException(baseError)
                    }
                    HttpStatusCode.Forbidden -> {
                        appDataStore.setAccessToken("")
                        appDataStore.setRefreshToken("")
                        appDataStore.setAuthRole(AuthRole.UnAuth)
                        val jsonString = exceptionResponse.bodyAsText()
                        val baseResponse = jsonString.decodeApiServiceResponseFromString()
                        val baseError = baseResponse.error ?: createBaseError()
                        throw ApiServiceException(baseError)
                    }
                    else -> {
                        val baseError = createBaseError(exception.message)
                        throw ApiServiceException(baseError)
                    }
                }
            }
        }
    }

    private fun createBaseError(message: String? = null): BaseError {
        val messageString = message ?: "Error."
        return BaseError(message = messageString)
    }
}