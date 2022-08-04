package com.boys.assets.ajaib.activity.users.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boys.assets.ajaib.activity.users.model.ReposModel
import com.boys.assets.ajaib.activity.users.model.UsersModel
import com.boys.assets.ajaib.activity.users.usecase.UsersRepoUC
import com.boys.assets.ajaib.activity.users.usecase.UsersUC
import com.boys.assets.ajaib.domain.model.ApiError
import com.boys.assets.ajaib.domain.usecase.UseCaseResponse

class UsersViewModel constructor(
    private val usersUC: UsersUC,
    private val usersRepoUC: UsersRepoUC
) : ViewModel()  {

    private val TAG = this::class.java.simpleName

    val onSuccessUsers = MutableLiveData<UsersModel>()
    val onErrorUsers = MutableLiveData<String>()
    
    val onSuccessRepo = MutableLiveData<ArrayList<ReposModel>>()
    val onErrorRepo = MutableLiveData<String>()

    fun getUser(login: String?) {
        usersUC.invoke(
            viewModelScope, login,
            object : UseCaseResponse<UsersModel> {
                override fun onSuccess(result: UsersModel) {
                    onSuccessUsers.value = result
                }

                override fun onError(apiError: ApiError) {
                    onErrorUsers.value = apiError.getErrorMessage()
                }
            },
        )
    }
    
    fun getRepo(login: String?) {
        usersRepoUC.invoke(
            viewModelScope, login,
            object : UseCaseResponse<ArrayList<ReposModel>> {
                override fun onSuccess(result: ArrayList<ReposModel>) {
                    onSuccessRepo.value = result
                }

                override fun onError(apiError: ApiError) {
                    onErrorRepo.value = apiError.getErrorMessage()
                }
            },
        )
    }
}