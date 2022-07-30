package com.adedom.data.repositories.profile

import com.adedom.data.models.response.user_profile.UserProfileResponse

interface UserProfileRepository {

    suspend fun callUserProfile(): UserProfileResponse?
}