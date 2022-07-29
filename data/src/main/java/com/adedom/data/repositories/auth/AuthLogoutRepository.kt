package com.adedom.data.repositories.auth

interface AuthLogoutRepository {
    suspend fun callLogout(): String?
    suspend fun setUnAuthRole()
}