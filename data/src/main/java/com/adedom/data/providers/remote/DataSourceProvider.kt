package com.adedom.data.providers.remote

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

class DataSourceProvider(
    private val apiServiceInterceptor: ApiServiceInterceptor,
) {

    fun getBaseUrl(): String {
        return "https://myfood-server.herokuapp.com/"
    }

    fun getHttpClient(dataSourceType: DataSourceType): HttpClient {
        return HttpClient(OkHttp) {
            engine {
                addInterceptor(apiServiceInterceptor)
                if (dataSourceType == DataSourceType.AUTHORIZATION) {
                    addInterceptor { chain ->
                        val request = chain.request()
                            .newBuilder()
                            .build()
                        chain.proceed(request)
                    }
                }
            }

            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 60_000
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
            }
        }
    }
}