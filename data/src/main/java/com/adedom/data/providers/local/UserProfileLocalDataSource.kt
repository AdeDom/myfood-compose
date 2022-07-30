package com.adedom.data.providers.local

import kotlinx.coroutines.flow.Flow
import myfood.database.UserProfileEntity

interface UserProfileLocalDataSource {
    fun getUserProfile(): Flow<UserProfileEntity?>
    suspend fun saveUserProfile(userProfile: UserProfileEntity)
    suspend fun deleteUserProfile()
}