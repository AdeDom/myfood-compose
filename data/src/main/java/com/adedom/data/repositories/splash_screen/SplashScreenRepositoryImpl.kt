package com.adedom.data.repositories.splash_screen

import com.adedom.data.providers.data_store.AppDataStore
import com.adedom.data.utils.AuthRole
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SplashScreenRepositoryImpl(
    private val appDataStore: AppDataStore,
) : SplashScreenRepository {

    override suspend fun getAuthRole(): AuthRole {
        return withContext(Dispatchers.IO) {
            appDataStore.getAuthRole()
        }
    }
}