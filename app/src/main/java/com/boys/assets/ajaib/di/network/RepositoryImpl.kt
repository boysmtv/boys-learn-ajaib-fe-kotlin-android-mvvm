package com.boys.assets.ajaib.di.network

import com.boys.assets.ajaib.activity.search.model.SearchModel
import com.boys.assets.ajaib.data.remote.ApiService

class RepositoryImpl (private val apiService: ApiService) : Repository {

    override suspend fun postSearch(): ArrayList<SearchModel> {
        return apiService.postSearch()
    }

}