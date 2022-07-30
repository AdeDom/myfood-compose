package com.adedom.domain.use_cases.main

import com.adedom.data.repositories.home.HomeRepository
import com.adedom.domain.models.category.CategoryModel
import com.adedom.domain.models.food.FoodModel
import com.adedom.domain.models.main.MainPageModel
import com.adedom.myfood.data.models.response.CategoryResponse
import com.adedom.myfood.data.models.response.FoodDetailResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class MainPageUseCase(
    private val homeRepository: HomeRepository,
) {

    suspend operator fun invoke(): MainPageModel {
        return coroutineScope {
            val categoryAll = homeRepository.callCategoryAll()

            val categoryList = categoryAll.map { mapCategoryToCategoryModel(it) }

            val foodList = categoryAll
                .map { category ->
                    async {
                        homeRepository.callFoodListByCategoryId(
                            categoryId = category.categoryId
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

    private fun mapCategoryToCategoryModel(category: CategoryResponse): CategoryModel {
        return CategoryModel(
            categoryId = category.categoryId,
            categoryName = category.categoryName,
            image = category.image,
        )
    }

    private fun mapFoodToFoodModel(food: FoodDetailResponse): FoodModel {
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