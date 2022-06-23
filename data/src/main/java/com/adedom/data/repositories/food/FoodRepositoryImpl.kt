package com.adedom.data.repositories.food

import com.adedom.data.models.response.food.Food
import com.adedom.data.providers.remote.food.FoodRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodRepositoryImpl(
    private val foodRemoteDataSource: FoodRemoteDataSource,
) : FoodRepository {

    override suspend fun callFoodListByCategoryId(categoryId: Int): List<Food> {
        return withContext(Dispatchers.IO) {
            val foodListResponse = foodRemoteDataSource.callFoodListByCategoryId(categoryId)
            foodListResponse.result ?: emptyList()
        }
    }
}