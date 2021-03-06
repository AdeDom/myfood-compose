package com.adedom.domain.use_cases.logout

import com.adedom.data.repositories.auth.AuthLogoutRepository
import com.adedom.data.repositories.profile.UserProfileRepository
import com.adedom.data.utils.ApiServiceException
import com.adedom.data.utils.Resource

class LogoutUseCase(
    private val authLogoutRepository: AuthLogoutRepository,
    private val userProfileRepository: UserProfileRepository,
) {

    suspend operator fun invoke(): Resource<Unit> {
        return try {
            authLogoutRepository.callLogout()
            authLogoutRepository.setUnAuthRole()
            userProfileRepository.deleteUserProfile()
            return Resource.Success(Unit)
        } catch (exception: ApiServiceException) {
            val baseError = exception.toBaseError()
            Resource.Error(baseError)
        }
    }
}