package com.adedom.data.providers.data_store

interface AppDataStore {

    suspend fun setAccessToken(accessToken: String)

    suspend fun getAccessToken(): String?

    suspend fun setRefreshToken(refreshToken: String)

    suspend fun getRefreshToken(): String?
}