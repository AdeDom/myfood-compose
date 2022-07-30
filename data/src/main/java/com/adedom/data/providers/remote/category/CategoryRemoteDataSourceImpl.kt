package com.adedom.data.providers.remote.category

import com.adedom.data.BuildConfig
import com.adedom.data.models.response.category.CategoryResponse
import com.adedom.data.providers.remote.DataSourceProvider
import com.adedom.data.providers.remote.DataSourceType
import io.ktor.client.call.*
import io.ktor.client.request.*

class CategoryRemoteDataSourceImpl(
    private val dataSourceProvider: DataSourceProvider,
) : CategoryRemoteDataSource {

    override suspend fun callCategoryAll(): CategoryResponse {
        return dataSourceProvider.getHttpClient(DataSourceType.UN_AUTHORIZATION)
            .get(BuildConfig.BASE_URL + "api/category/getCategoryAll")
            .body()
    }
}