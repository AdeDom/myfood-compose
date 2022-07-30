package com.adedom.data.repositories.profile

import com.adedom.data.providers.local.UserProfileLocalDataSource
import com.adedom.data.providers.remote.profile.ProfileRemoteDataSource
import com.adedom.myfood.data.models.response.UserProfileResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import myfood.database.UserProfileEntity

class UserProfileRepositoryImpl(
    private val userProfileLocalDataSource: UserProfileLocalDataSource,
    private val profileRemoteDataSource: ProfileRemoteDataSource,
) : UserProfileRepository {

    override suspend fun callUserProfile(): UserProfileResponse? {
        return withContext(Dispatchers.IO) {
            val userProfileResponse = profileRemoteDataSource.callUserProfile()
            userProfileResponse.result
        }
    }

    override fun getUserProfile(): Flow<UserProfileEntity?> {
        return userProfileLocalDataSource.getUserProfile()
    }

    override suspend fun saveUserProfile(userProfile: UserProfileEntity) {
        return withContext(Dispatchers.IO) {
            userProfileLocalDataSource.saveUserProfile(userProfile)
        }
    }

    override suspend fun deleteUserProfile() {
        return withContext(Dispatchers.IO) {
            userProfileLocalDataSource.deleteUserProfile()
        }
    }
}