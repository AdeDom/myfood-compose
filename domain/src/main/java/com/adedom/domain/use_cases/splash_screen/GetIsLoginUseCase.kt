package com.adedom.domain.use_cases.splash_screen

import com.adedom.data.repositories.splash_screen.SplashScreenRepository

class GetIsLoginUseCase(
    private val splashScreenRepository: SplashScreenRepository,
) {

    operator fun invoke(): Boolean {
        return splashScreenRepository.isLogin()
    }
}