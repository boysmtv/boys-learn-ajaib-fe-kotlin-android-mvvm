package com.boys.assets.ajaib.di

import com.boys.assets.ajaib.activity.search.usecase.SearchUC
import com.boys.assets.ajaib.activity.users.usecase.UsersRepoUC
import com.boys.assets.ajaib.activity.users.usecase.UsersUC
import com.boys.assets.ajaib.di.network.Repository

fun getSearchUseCase(repository: Repository): SearchUC {
    return SearchUC(repository)
}

fun getUsersUseCase(repository: Repository): UsersUC {
    return UsersUC(repository)
}

fun getUsersRepoUseCase(repository: Repository): UsersRepoUC {
    return UsersRepoUC(repository)
}
