package com.adedom.domain.di

import com.adedom.domain.use_cases.login.LoginUseCase
import com.adedom.domain.use_cases.logout.LogoutUseCase
import com.adedom.domain.use_cases.main.MainPageUseCase
import com.adedom.domain.use_cases.splash_screen.GetIsAuthUseCase
import com.adedom.domain.use_cases.user_profile.GetUserProfileUseCase
import com.adedom.domain.use_cases.validate.ValidateEmailUseCase
import com.adedom.domain.use_cases.validate.ValidatePasswordUseCase
import com.adedom.domain.use_cases.welcome.WelcomeGuestRoleUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val domainModule = DI.Module(name = "domain") {

    bindProvider { GetIsAuthUseCase(instance()) }
    bindProvider { ValidateEmailUseCase() }
    bindProvider { ValidatePasswordUseCase() }
    bindProvider { LoginUseCase(instance(), instance()) }
    bindProvider { LogoutUseCase(instance()) }
    bindProvider { WelcomeGuestRoleUseCase(instance()) }
    bindProvider { MainPageUseCase(instance()) }
    bindProvider { GetUserProfileUseCase(instance()) }
}