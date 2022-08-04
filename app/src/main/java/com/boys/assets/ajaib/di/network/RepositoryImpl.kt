package com.boys.assets.ajaib.di.network

import com.boys.assets.ajaib.activity.search.model.SearchModel
import com.boys.assets.ajaib.activity.users.model.ReposModel
import com.boys.assets.ajaib.activity.users.model.UsersModel
import com.boys.assets.ajaib.data.remote.ApiService

class RepositoryImpl (private val apiService: ApiService) : Repository {

    override suspend fun postSearch(): ArrayList<SearchModel> {
        return apiService.postSearch()
    }

    override suspend fun getUsers(users: String): UsersModel {
        return apiService.getUsers(users)
    }

    override suspend fun getRepos(users: String): ArrayList<ReposModel> {
        return apiService.getRepos(users)
    }

}