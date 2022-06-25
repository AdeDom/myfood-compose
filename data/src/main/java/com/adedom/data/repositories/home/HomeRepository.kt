package com.adedom.data.repositories.home

import com.adedom.data.models.response.category.Category
import com.adedom.data.models.response.food.Food

interface HomeRepository {

    suspend fun callCategoryAll(): List<Category>

    suspend fun callFoodListByCategoryId(categoryId: Int): List<Food>
}