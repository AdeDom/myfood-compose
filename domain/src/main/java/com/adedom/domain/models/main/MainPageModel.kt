package com.adedom.domain.models.main

import com.adedom.domain.models.category.CategoryModel
import com.adedom.domain.models.food.FoodModel

data class MainPageModel(
    val categoryList: List<CategoryModel>,
    val foodList: List<FoodModel>,
)