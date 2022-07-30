package com.adedom.data.repositories.home

import com.adedom.myfood.data.models.response.CategoryResponse
import com.adedom.myfood.data.models.response.FoodDetailResponse

interface HomeRepository {

    suspend fun callCategoryAll(): List<CategoryResponse>

    suspend fun callFoodListByCategoryId(categoryId: Int): List<FoodDetailResponse>
}