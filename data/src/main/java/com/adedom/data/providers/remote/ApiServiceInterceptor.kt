package com.adedom.data.providers.remote

import com.adedom.data.BuildConfig
import com.adedom.data.models.error.BaseError
import com.adedom.data.models.request.token.TokenRequest
import com.adedom.data.models.response.BaseResponse
import com.adedom.data.models.response.token.TokenResponse
import com.adedom.data.providers.data_store.AppDataStore
import com.adedom.data.utils.ApiServiceException
import com.adedom.data.utils.AuthRole
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.content.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject

class ApiServiceInterceptor(
    private val appDataStore: AppDataStore,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (response.isSuccessful) {
            return response
        } else {
            when (response.code) {
                HttpStatusCode.BadRequest.value -> {
                    val jsonString = JSONObject(response.body?.string().orEmpty()).toString()
                    val baseResponse = jsonString.decodeApiServiceResponseFromString()
                    val baseError = baseResponse.error ?: createBaseError()
                    throw ApiServiceException(baseError)
                }
                HttpStatusCode.Unauthorized.value -> {
                    val jsonString = JSONObject(response.body?.string().orEmpty()).toString()
                    return callRefreshToken(chain, jsonString)
                }
                HttpStatusCode.Forbidden.value -> runBlocking(Dispatchers.IO) {
                    appDataStore.setAccessToken("")
                    appDataStore.setRefreshToken("")
                    appDataStore.setAuthRole(AuthRole.UnAuth)
                    val jsonString = JSONObject(response.body?.string().orEmpty()).toString()
                    val baseResponse = jsonString.decodeApiServiceResponseFromString()
                    val baseError = baseResponse.error ?: createBaseError()
                    throw ApiServiceException(baseError)
                }
                in 500..599 -> {
                    val baseError = createBaseError(response.message)
                    throw ApiServiceException(baseError)
                }
                else -> {
                    val baseError = createBaseError(response.message)
                    throw ApiServiceException(baseError)
                }
            }
        }
    }

    private fun createBaseError(message: String? = null): BaseError {
        val messageString = message ?: "Error."
        return BaseError(message = messageString)
    }

    private fun callRefreshToken(chain: Interceptor.Chain, jsonString: String): Response {
        return runBlocking(Dispatchers.IO) {
            val hasRefreshToken = appDataStore.getRefreshToken().isNullOrBlank()
            if (hasRefreshToken) {
                appDataStore.setAccessToken("")
                appDataStore.setRefreshToken("")
                appDataStore.setAuthRole(AuthRole.UnAuth)
                val baseResponse = jsonString.decodeApiServiceResponseFromString()
                val baseError = baseResponse.error ?: createBaseError()
                throw ApiServiceException(baseError)
            } else {
                val tokenRequest = TokenRequest(
                    accessToken = appDataStore.getAccessToken(),
                    refreshToken = appDataStore.getRefreshToken(),
                )
                val tokenResponse: BaseResponse<TokenResponse> = getHttpClient()
                    .post("${BuildConfig.BASE_URL}/api/auth/refreshtoken") {
                        body = TextContent(
                            text = Json.encodeToString(tokenRequest),
                            contentType = ContentType.Application.Json
                        )
                    }

                val accessToken = tokenResponse.result?.accessToken.orEmpty()
                val refreshToken = tokenResponse.result?.refreshToken.orEmpty()
                appDataStore.setAccessToken(accessToken)
                appDataStore.setRefreshToken(refreshToken)
                val request = chain.request()
                    .newBuilder()
                    .removeHeader(HttpHeaders.Authorization)
                    .addHeader(HttpHeaders.Authorization, "Bearer $accessToken")
                    .build()
                chain.proceed(request)
            }
        }
    }

    private fun getHttpClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
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
        }
    }
}