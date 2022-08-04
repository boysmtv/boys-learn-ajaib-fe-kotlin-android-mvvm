package com.boys.assets.ajaib.activity.users.usecase

import com.boys.assets.ajaib.activity.users.model.UsersModel
import com.boys.assets.ajaib.di.network.Repository
import com.boys.assets.ajaib.domain.usecase.UseCase

class UsersUC constructor(
    private val repository: Repository
) : UseCase<UsersModel, Any?>() {

    private val TAG = this::class.java.simpleName

    override suspend fun run(params: Any?): UsersModel {
        return repository.getUsers(params as String)
    }

}