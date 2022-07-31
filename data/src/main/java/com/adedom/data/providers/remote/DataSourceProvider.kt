package com.adedom.data.providers.remote

import com.adedom.data.BuildConfig
import com.adedom.data.providers.data_store.AppDataStore
import com.adedom.data.utils.ApiServiceException
import com.adedom.data.utils.AuthRole
import com.adedom.myfood.data.models.base.BaseError
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
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