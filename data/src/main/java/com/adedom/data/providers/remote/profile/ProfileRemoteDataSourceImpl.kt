package com.adedom.data.providers.remote.profile

import com.adedom.data.BuildConfig
import com.adedom.data.models.response.BaseResponse
import com.adedom.data.models.response.user_profile.UserProfileResponse
import com.adedom.data.providers.remote.DataSourceProvider
import com.adedom.data.providers.remote.DataSourceType
import io.ktor.client.request.*

class ProfileRemoteDataSourceImpl(
    private val dataSourceProvider: DataSourceProvider,
) : ProfileRemoteDataSource {

    override suspend fun callUserProfile(): BaseResponse<UserProfileResponse> {
        return dataSourceProvider.getHttpClient(DataSourceType.AUTHORIZATION)
            .get(BuildConfig.BASE_URL + "api/profile/user")
    }
}