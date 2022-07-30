package com.adedom.data.providers.remote.profile

import com.adedom.data.BuildConfig
import com.adedom.data.providers.remote.DataSourceProvider
import com.adedom.data.providers.remote.DataSourceType
import com.adedom.myfood.data.models.base.BaseResponse
import com.adedom.myfood.data.models.response.UserProfileResponse
import io.ktor.client.call.*
import io.ktor.client.request.*

class ProfileRemoteDataSourceImpl(
    private val dataSourceProvider: DataSourceProvider,
) : ProfileRemoteDataSource {

    override suspend fun callUserProfile(): BaseResponse<UserProfileResponse> {
        return dataSourceProvider.getHttpClient(DataSourceType.AUTHORIZATION)
            .get(BuildConfig.BASE_URL + "api/profile/user")
            .body()
    }
}