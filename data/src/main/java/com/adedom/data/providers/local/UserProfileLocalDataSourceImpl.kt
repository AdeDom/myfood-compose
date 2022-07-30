package com.adedom.data.providers.local

import com.adedom.myfood.MyFoodDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import myfood.database.MyFoodDatabaseQueries
import myfood.database.UserProfileEntity

class UserProfileLocalDataSourceImpl(
    db: MyFoodDatabase,
) : UserProfileLocalDataSource {

    private val queries: MyFoodDatabaseQueries = db.myFoodDatabaseQueries

    override fun getUserProfile(): Flow<UserProfileEntity?> {
        return queries.getUserProfile().asFlow().mapToOneOrNull(Dispatchers.IO)
    }

    override suspend fun saveUserProfile(userProfile: UserProfileEntity) {
        return queries.saveUserProfile(
            userId = userProfile.userId,
            email = userProfile.email,
            name = userProfile.name,
            mobileNo = userProfile.mobileNo,
            address = userProfile.address,
            image = userProfile.image,
            status = userProfile.status,
            created = userProfile.created,
            updated = userProfile.updated,
        )
    }

    override suspend fun deleteUserProfile() {
        return queries.deleteUserProfile()
    }
}