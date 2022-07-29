package com.adedom.data.providers.remote

import com.adedom.data.BuildConfig
import com.adedom.data.providers.data_store.AppDataStore
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class DataSourceProvider(
    private val dataStore: AppDataStore,
    private val apiServiceInterceptor: ApiServiceInterceptor,
) {

    fun getHttpClient(dataSourceType: DataSourceType): HttpClient {
        return HttpClient(OkHttp) {
            engine {
                addInterceptor(apiServiceInterceptor)
                if (dataSourceType == DataSourceType.AUTHORIZATION) {
                    addInterceptor { chain ->
                        val accessToken = runBlocking(Dispatchers.IO) {
                            dataStore.getAccessToken().orEmpty()
                        }
                        val request = chain.request()
                            .newBuilder()
                            .addHeader(HttpHeaders.Authorization, "Bearer $accessToken")
                            .build()
                        chain.proceed(request)
                    }
                }
            }

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