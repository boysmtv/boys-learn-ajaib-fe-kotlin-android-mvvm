package com.boys.assets.ajaib.di

import com.boys.assets.ajaib.data.remote.ApiService
import com.boys.assets.ajaib.di.network.Repository
import com.boys.assets.ajaib.di.network.RepositoryImpl

fun createRepository(apiService: ApiService): Repository {
    return RepositoryImpl(apiService)
}