package com.adedom.data.repositories.profile

import com.adedom.myfood.data.models.response.UserProfileResponse
import kotlinx.coroutines.flow.Flow
import myfood.database.UserProfileEntity

interface UserProfileRepository {

    suspend fun callUserProfile(): UserProfileResponse?

    fun getUserProfile(): Flow<UserProfileEntity?>

    suspend fun saveUserProfile(userProfile: UserProfileEntity)

    suspend fun deleteUserProfile()
}