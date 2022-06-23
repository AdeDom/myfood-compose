package com.adedom.domain.use_cases.main

import com.adedom.data.models.response.category.Category
import com.adedom.data.models.response.food.Food
import com.adedom.data.repositories.category.CategoryRepository
import com.adedom.data.repositories.food.FoodRepository
import com.adedom.domain.models.category.CategoryModel
import com.adedom.domain.models.food.FoodModel
import com.adedom.domain.models.main.MainPageModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class MainPageUseCase(
    private val categoryRepository: CategoryRepository,
    private val foodRepository: FoodRepository,
) {

    suspend operator fun invoke(): MainPageModel {
        return coroutineScope {
            val categoryAll = categoryRepository.callCategoryAll()

            val categoryList = categoryAll.map { mapCategoryToCategoryModel(it) }

            val foodList = categoryAll
                .map { category ->
                    async {
                        foodRepository.callFoodListByCategoryId(
                            categoryId = category.categoryId ?: CATEGORY_RECOMMEND
                        )
                    }
                }
                .awaitAll()
                .flatten()
                .map { mapFoodToFoodModel(it) }
                .filter { food ->
                    food.categoryId == CATEGORY_RECOMMEND
                }

            MainPageModel(
                categoryList = categoryList,
                foodList = foodList,
            )
        }
    }

    private fun mapCategoryToCategoryModel(category: Category): CategoryModel {
        return CategoryModel(
            categoryId = category.categoryId ?: 0,
            categoryName = category.categoryName.orEmpty(),
            image = category.image.orEmpty(),
        )
    }

    private fun mapFoodToFoodModel(food: Food): FoodModel {
        return FoodModel(
            foodId = food.foodId,
            foodName = food.foodName,
            alias = "alias",
            image = food.image,
            ratingScoreCount = "4.9",
            categoryId = food.categoryId,
        )
    }

    companion object {
        const val CATEGORY_RECOMMEND = 1
    }
}