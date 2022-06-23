package com.adedom.data.repositories.category

import com.adedom.data.models.response.category.Category
import com.adedom.data.providers.remote.category.CategoryRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepositoryImpl(
    private val categoryRemoteDataSource: CategoryRemoteDataSource,
) : CategoryRepository {

    override suspend fun callCategoryAll(): List<Category> {
        return withContext(Dispatchers.IO) {
            val categoryAllResponse = categoryRemoteDataSource.callCategoryAll()
            categoryAllResponse.result ?: emptyList()
        }
    }
}