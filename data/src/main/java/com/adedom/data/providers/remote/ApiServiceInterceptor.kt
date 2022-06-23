package com.adedom.data.providers.remote

import com.adedom.data.models.error.BaseError
import com.adedom.data.utils.ApiServiceException
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject

class ApiServiceInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (response.isSuccessful) {
            val jsonString = response.peekBody(Long.MAX_VALUE).string()
            val baseResponse = jsonString.decodeApiServiceResponseFromString()
            val status = baseResponse.status
            return if (status == "success") {
                response
            } else {
                val baseError = baseResponse.error ?: createBaseError()
                throw ApiServiceException(baseError)
            }
        } else {
            try {
                val jsonString = JSONObject(response.body?.string().orEmpty()).toString()
                val baseResponse = jsonString.decodeApiServiceResponseFromString()
                val baseError = baseResponse.error ?: createBaseError()
                throw ApiServiceException(baseError)
            } catch (e: JSONException) {
                val baseError = createBaseError(e.message)
                throw ApiServiceException(baseError)
            }
        }
    }

    private fun createBaseError(message: String? = null): BaseError {
        val messageString = message ?: "Error."
        return BaseError(message = messageString)
    }
}