package com.boys.assets.ajaib.activity.search.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boys.assets.ajaib.activity.search.model.SearchModel
import com.boys.assets.ajaib.activity.search.usecase.SearchUC
import com.boys.assets.ajaib.domain.model.ApiError
import com.boys.assets.ajaib.domain.usecase.UseCaseResponse

class SearchViewModel constructor(private val useCase: SearchUC) : ViewModel()  {

    private val TAG = this::class.java.simpleName

    val onSuccess = MutableLiveData<ArrayList<SearchModel>>()
    val onError = MutableLiveData<String>()

    fun doIt() {
        useCase.invoke(
            viewModelScope, null,
            object : UseCaseResponse<ArrayList<SearchModel>> {
                override fun onSuccess(result: ArrayList<SearchModel>) {
                    onSuccess.value = result
                }

                override fun onError(apiError: ApiError) {
                    onError.value = apiError.getErrorMessage()
                }
            },
        )
    }
}