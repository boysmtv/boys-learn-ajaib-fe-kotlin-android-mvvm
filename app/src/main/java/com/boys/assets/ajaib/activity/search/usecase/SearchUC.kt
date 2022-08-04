package com.boys.assets.ajaib.activity.search.usecase

import com.boys.assets.ajaib.activity.search.model.SearchModel
import com.boys.assets.ajaib.di.network.Repository
import com.boys.assets.ajaib.domain.usecase.UseCase

class SearchUC constructor(
    private val repository: Repository
) : UseCase<ArrayList<SearchModel>, Any?>() {

    private val TAG = this::class.java.simpleName

    override suspend fun run(params: Any?): ArrayList<SearchModel> {
        return repository.postSearch()
    }

}