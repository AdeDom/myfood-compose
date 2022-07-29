package com.adedom.data.repositories.auth

import com.adedom.data.providers.data_store.AppDataStore
import com.adedom.data.providers.remote.auth.AuthRemoteDataSource
import com.adedom.data.utils.AuthRole
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthLogoutRepositoryImpl(
    private val appDataStore: AppDataStore,
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthLogoutRepository {

    override suspend fun callLogout(): String? {
        return withContext(Dispatchers.IO) {
            val logoutResponse = authRemoteDataSource.callLogout()
            logoutResponse.result
        }
    }

    override suspend fun setUnAuthRole() {
        return withContext(Dispatchers.IO) {
            val authRole = AuthRole.UnAuth
            appDataStore.setAuthRole(authRole)
        }
    }
}