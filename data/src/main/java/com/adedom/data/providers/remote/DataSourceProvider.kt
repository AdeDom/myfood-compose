package com.adedom.data.providers.remote

import com.adedom.data.BuildConfig
import com.adedom.data.providers.data_store.AppDataStore
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

class DataSourceProvider(
    private val appDataStore: AppDataStore,
    private val apiServiceInterceptor: ApiServiceInterceptor,
) {

    fun getHttpClient(dataSourceType: DataSourceType): HttpClient {
        return HttpClient(OkHttp) {
            engine {
                addInterceptor(apiServiceInterceptor)
                if (dataSourceType == DataSourceType.AUTHORIZATION) {
                    addInterceptor { chain ->
                        val accessToken = runBlocking(Dispatchers.IO) {
                            appDataStore.getAccessToken().orEmpty()
                        }
                        val request = chain.request()
                            .newBuilder()
                            .addHeader(HttpHeaders.Authorization, "Bearer $accessToken")
                            .build()
                        chain.proceed(request)
                    }
                }
            }

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
        }
    }
}