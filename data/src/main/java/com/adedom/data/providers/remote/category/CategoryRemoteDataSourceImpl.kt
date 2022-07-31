package com.adedom.data.providers.remote.category

import com.adedom.data.BuildConfig
import com.adedom.data.providers.remote.DataSourceProvider
import com.adedom.myfood.data.models.base.BaseResponse
import com.adedom.myfood.data.models.response.CategoryResponse
import io.ktor.client.call.*
import io.ktor.client.request.*

class CategoryRemoteDataSourceImpl(
    private val dataSourceProvider: DataSourceProvider,
) : CategoryRemoteDataSource {

    override suspend fun callCategoryAll(): BaseResponse<List<CategoryResponse>> {
        return dataSourceProvider.getHttpClient()
            .get(BuildConfig.BASE_URL + "api/category/getCategoryAll")
            .body()
    }
}