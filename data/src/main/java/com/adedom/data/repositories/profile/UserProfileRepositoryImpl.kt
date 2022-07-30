package com.adedom.data.repositories.profile

import com.adedom.data.models.response.user_profile.UserProfileResponse
import com.adedom.data.providers.remote.profile.ProfileRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserProfileRepositoryImpl(
    private val profileRemoteDataSource: ProfileRemoteDataSource,
) : UserProfileRepository {

    override suspend fun callUserProfile(): UserProfileResponse? {
        return withContext(Dispatchers.IO) {
            val userProfileResponse = profileRemoteDataSource.callUserProfile()
            userProfileResponse.result
        }
    }
}