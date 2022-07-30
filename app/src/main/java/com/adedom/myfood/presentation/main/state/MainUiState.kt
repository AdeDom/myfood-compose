package com.adedom.myfood.presentation.main.state

import com.adedom.domain.models.user_profile.UserProfileModel

sealed interface MainUiState {
    object Initial : MainUiState
    data class ShowUserProfile(
        val userProfile: UserProfileModel,
    ) : MainUiState
}