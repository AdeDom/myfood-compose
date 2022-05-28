package com.adedom.domain.use_cases.get_data

import com.adedom.data.repositories.default_repository.DefaultRepository

class GetDataUseCase(
    private val defaultRepository: DefaultRepository,
) {

    operator fun invoke(): String {
        return defaultRepository.getData()
    }
}