package com.boys.assets.ajaib.di

import com.boys.assets.ajaib.activity.search.vm.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureStag = module {
    viewModel { SearchViewModel(get()) }

    single { getSearchUseCase(get()) }
    single { createRepository(get()) }
}
