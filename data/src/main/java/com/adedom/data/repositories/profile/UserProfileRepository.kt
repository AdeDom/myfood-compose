package com.adedom.data.repositories.profile

import com.adedom.data.models.response.user_profile.UserProfileResponse
import kotlinx.coroutines.flow.Flow
import myfood.database.UserProfileEntity

interface UserProfileRepository {

    suspend fun callUserProfile(): UserProfileResponse?

    fun getUserProfile(): Flow<UserProfileEntity?>

    suspend fun saveUserProfile(userProfile: UserProfileEntity)

    suspend fun deleteUserProfile()
}