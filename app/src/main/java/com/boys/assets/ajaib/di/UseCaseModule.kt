package com.boys.assets.ajaib.di

import com.boys.assets.ajaib.activity.search.usecase.SearchUC
import com.boys.assets.ajaib.di.network.Repository

fun getSearchUseCase(repository: Repository): SearchUC {
    return SearchUC(repository)
}
