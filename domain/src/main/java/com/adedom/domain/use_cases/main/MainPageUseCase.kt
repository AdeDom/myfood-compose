package com.adedom.domain.use_cases.main

import com.adedom.data.repositories.category.CategoryRepository
import com.adedom.domain.models.category.CategoryModel
import com.adedom.domain.models.main.MainPageModel

class MainPageUseCase(
    private val categoryRepository: CategoryRepository,
) {

    suspend operator fun invoke(): MainPageModel {
        val categoryAll = categoryRepository.callCategoryAll()
        return MainPageModel(
            categoryList = categoryAll.map { category ->
                CategoryModel(
                    categoryId = category.categoryId ?: 0,
                    categoryName = category.categoryName.orEmpty(),
                    image = category.image.orEmpty(),
                )
            },
        )
    }
}