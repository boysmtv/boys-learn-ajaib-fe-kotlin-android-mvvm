package com.boys.assets.ajaib.data.remote

import com.boys.assets.ajaib.activity.search.model.SearchModel
import com.boys.assets.ajaib.activity.users.model.ReposModel
import com.boys.assets.ajaib.activity.users.model.UsersModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("users")
    suspend fun postSearch() : ArrayList<SearchModel>

    @Headers("Content-Type: application/json")
    @GET("users/{users}")
    suspend fun getUsers(@Path("users") users: String?) : UsersModel

    @Headers("Content-Type: application/json")
    @GET("users/{users}/repos")
    suspend fun getRepos(@Path("users") users: String?) : ArrayList<ReposModel>

}