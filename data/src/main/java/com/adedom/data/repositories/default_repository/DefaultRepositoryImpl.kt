package com.adedom.data.repositories.default_repository

class DefaultRepositoryImpl : DefaultRepository {

    override fun getData(): String {
        return "Hello, my food - compose."
    }
}