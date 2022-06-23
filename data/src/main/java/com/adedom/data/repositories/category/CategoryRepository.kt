package com.adedom.data.repositories.category

import com.adedom.data.models.response.category.Category

interface CategoryRepository {

    suspend fun callCategoryAll(): List<Category>
}