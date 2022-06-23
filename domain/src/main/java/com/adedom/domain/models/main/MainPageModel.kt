package com.adedom.domain.models.main

import com.adedom.domain.models.category.CategoryModel

data class MainPageModel(
    val categoryList: List<CategoryModel>,
)