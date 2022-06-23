package com.adedom.domain.use_cases.welcome

import com.adedom.data.repositories.welcome.WelcomeRepository

class WelcomeGuestRoleUseCase(
    private val welcomeRepository: WelcomeRepository,
) {

    suspend operator fun invoke() {
        return welcomeRepository.setGuestRole()
    }
}