package com.adedom.data.repositories.splash_screen

import com.adedom.data.utils.AuthRole

interface SplashScreenRepository {

    suspend fun getAuthRole(): AuthRole
}