package com.adedom.domain.usecases

import com.adedom.data.repositories.DefaultRepository

class GetDataUseCase(
    private val defaultRepository: DefaultRepository,
) {

    operator fun invoke(): String {
        return defaultRepository.getData()
    }
}