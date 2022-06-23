package com.adedom.data.providers.remote.food

import com.adedom.data.models.response.food.FoodResponse

interface FoodRemoteDataSource {

    suspend fun callFoodListByCategoryId(categoryId: Int): FoodResponse
}