package com.adedom.data.providers.remote.category

import com.adedom.data.models.response.category.CategoryResponse

interface CategoryRemoteDataSource {

    suspend fun callCategoryAll(): CategoryResponse
}