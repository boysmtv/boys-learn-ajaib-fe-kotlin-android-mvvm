package com.boys.assets.ajaib.di

import com.boys.assets.ajaib.activity.search.vm.SearchViewModel
import com.boys.assets.ajaib.activity.users.vm.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureStag = module {
    // for search
    viewModel { SearchViewModel(get()) }
    single { getSearchUseCase(get()) }

    // for get users
    viewModel { UsersViewModel(get(), get()) }
    single { getUsersUseCase(get()) }
    single { getUsersRepoUseCase(get()) }

    // create repository
    single { createRepository(get()) }
}
