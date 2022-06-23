package com.adedom.data.repositories.food

import com.adedom.data.models.response.food.Food

interface FoodRepository {

    suspend fun callFoodListByCategoryId(categoryId: Int): List<Food>
}