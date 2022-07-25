package com.adedom.data.providers.remote.food

import com.adedom.data.BuildConfig
import com.adedom.data.models.response.food.FoodResponse
import com.adedom.data.providers.remote.DataSourceProvider
import com.adedom.data.providers.remote.DataSourceType
import io.ktor.client.request.*

class FoodRemoteDataSourceImpl(
    private val dataSourceProvider: DataSourceProvider,
) : FoodRemoteDataSource {

    override suspend fun callFoodListByCategoryId(categoryId: Int): FoodResponse {
        return dataSourceProvider.getHttpClient(DataSourceType.UN_AUTHORIZATION)
            .get(BuildConfig.BASE_URL + "api/food/getFoodByCategoryId?categoryId=$categoryId")
    }
}