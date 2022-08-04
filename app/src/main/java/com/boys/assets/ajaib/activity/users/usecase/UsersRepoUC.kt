package com.boys.assets.ajaib.activity.users.usecase

import com.boys.assets.ajaib.activity.users.model.ReposModel
import com.boys.assets.ajaib.activity.users.model.UsersModel
import com.boys.assets.ajaib.di.network.Repository
import com.boys.assets.ajaib.domain.usecase.UseCase

class UsersRepoUC constructor(
    private val repository: Repository
) : UseCase<ArrayList<ReposModel>, Any?>() {

    private val TAG = this::class.java.simpleName

    override suspend fun run(params: Any?): ArrayList<ReposModel> {
        return repository.getRepos(params as String)
    }

}