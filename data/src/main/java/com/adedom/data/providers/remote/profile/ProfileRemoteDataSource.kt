package com.adedom.data.providers.remote.profile

import com.adedom.data.models.response.BaseResponse
import com.adedom.data.models.response.user_profile.UserProfileResponse

interface ProfileRemoteDataSource {

    suspend fun callUserProfile(): BaseResponse<UserProfileResponse>
}