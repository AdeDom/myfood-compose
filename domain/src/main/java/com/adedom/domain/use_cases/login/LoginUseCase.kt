package com.adedom.domain.use_cases.login

import com.adedom.data.models.error.AppErrorCode
import com.adedom.data.models.error.BaseError
import com.adedom.data.models.request.login.LoginRequest
import com.adedom.data.models.response.user_profile.UserProfileResponse
import com.adedom.data.repositories.auth.AuthLoginRepository
import com.adedom.data.repositories.profile.UserProfileRepository
import com.adedom.data.utils.ApiServiceException
import com.adedom.data.utils.Resource
import myfood.database.UserProfileEntity

class LoginUseCase(
    private val authLoginRepository: AuthLoginRepository,
    private val userProfileRepository: UserProfileRepository,
) {

    suspend operator fun invoke(email: String, password: String): Resource<Unit> {
        return try {
            val loginRequest = LoginRequest(email, password)
            val tokenResponse = authLoginRepository.callLogin(loginRequest)
            val accessToken = tokenResponse?.accessToken
            val refreshToken = tokenResponse?.refreshToken
            if (accessToken != null && refreshToken != null) {
                authLoginRepository.saveToken(accessToken, refreshToken)
                authLoginRepository.saveAuthRole()

                val userProfile = userProfileRepository.callUserProfile()
                val userProfileEntity = mapUserProfileToUserProfileEntity(userProfile)
                userProfileRepository.saveUserProfile(userProfileEntity)
                Resource.Success(Unit)
            } else {
                val code = AppErrorCode.TokenIsNull.code
                val baseError = BaseError(code = code)
                Resource.Error(baseError)
            }
        } catch (exception: ApiServiceException) {
            val baseError = exception.toBaseError()
            Resource.Error(baseError)
        }
    }

    private fun mapUserProfileToUserProfileEntity(userProfile: UserProfileResponse?): UserProfileEntity {
        return UserProfileEntity(
            userProfile?.userId.orEmpty(),
            userProfile?.email.orEmpty(),
            userProfile?.name.orEmpty(),
            userProfile?.mobileNo,
            userProfile?.address,
            userProfile?.image,
            userProfile?.status.orEmpty(),
            userProfile?.created.orEmpty(),
            userProfile?.updated,
        )
    }
}