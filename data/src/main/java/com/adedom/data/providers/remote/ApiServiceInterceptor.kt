package com.adedom.data.providers.remote

import com.adedom.data.models.error.BaseError
import com.adedom.data.utils.ApiServiceException
import io.ktor.http.*
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject

class ApiServiceInterceptor : Interceptor {

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
                    val error = createBaseError()
                    throw ApiServiceException(error)
                }
                HttpStatusCode.Forbidden.value -> {
                    val error = createBaseError()
                    throw ApiServiceException(error)
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
}