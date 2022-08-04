package com.boys.assets.ajaib.domain.usecase

import com.boys.assets.ajaib.domain.model.ApiError

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(apiError: ApiError)

}

