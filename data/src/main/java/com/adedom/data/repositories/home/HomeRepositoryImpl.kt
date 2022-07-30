package com.adedom.data.repositories.home

import com.adedom.data.providers.remote.category.CategoryRemoteDataSource
import com.adedom.data.providers.remote.food.FoodRemoteDataSource
import com.adedom.myfood.data.models.response.CategoryResponse
import com.adedom.myfood.data.models.response.FoodDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepositoryImpl(
    private val categoryRemoteDataSource: CategoryRemoteDataSource,
    private val foodRemoteDataSource: FoodRemoteDataSource,
) : HomeRepository {

    override suspend fun callCategoryAll(): List<CategoryResponse> {
        return withContext(Dispatchers.IO) {
            val categoryAllResponse = categoryRemoteDataSource.callCategoryAll()
            categoryAllResponse.result ?: emptyList()
        }
    }

    override suspend fun callFoodListByCategoryId(categoryId: Int): List<FoodDetailResponse> {
        return withContext(Dispatchers.IO) {
            val foodListResponse = foodRemoteDataSource.callFoodListByCategoryId(categoryId)
            foodListResponse.result ?: emptyList()
        }
    }
}