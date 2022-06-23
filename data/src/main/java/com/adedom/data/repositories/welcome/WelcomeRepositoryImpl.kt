package com.adedom.data.repositories.welcome

import com.adedom.data.providers.data_store.AppDataStore
import com.adedom.data.utils.AuthRole
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WelcomeRepositoryImpl(
    private val appDataStore: AppDataStore,
) : WelcomeRepository {

    override suspend fun setGuestRole() {
        withContext(Dispatchers.IO) {
            val authRole = AuthRole.Guest
            appDataStore.setAuthRole(authRole)
        }
    }
}