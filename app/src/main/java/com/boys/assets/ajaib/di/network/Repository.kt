package com.boys.assets.ajaib.di.network

import com.boys.assets.ajaib.activity.search.model.SearchModel
import com.boys.assets.ajaib.activity.users.model.UsersModel

interface Repository {

    suspend fun postSearch(): ArrayList<SearchModel>

    suspend fun getUsers(users: String): UsersModel

}