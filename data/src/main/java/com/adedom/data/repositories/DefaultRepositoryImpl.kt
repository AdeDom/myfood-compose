package com.adedom.data.repositories

class DefaultRepositoryImpl : DefaultRepository {

    override fun getData(): String {
        return "Hello, my food - compose."
    }
}