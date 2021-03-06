package com.adedom.domain.use_cases.splash_screen

import com.adedom.data.repositories.splash_screen.SplashScreenRepository
import com.adedom.data.utils.AuthRole

class GetIsAuthUseCase(
    private val splashScreenRepository: SplashScreenRepository,
) {

    suspend operator fun invoke(): Boolean {
        return when (splashScreenRepository.getAuthRole()) {
            AuthRole.Auth -> true
            AuthRole.UnAuth -> false
            AuthRole.Guest -> true
            AuthRole.Unknown -> false
        }
    }
}