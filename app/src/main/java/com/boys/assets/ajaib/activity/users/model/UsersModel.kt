package com.boys.assets.ajaib.activity.users.model

import com.google.gson.annotations.SerializedName

data class UsersModel (

    @SerializedName("login")
    val login: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("node_id")
    val node_id: String? = null,

    @SerializedName("avatar_url")
    val avatar_url: String? = null,

    @SerializedName("gravatar_id")
    val gravatar_id: String? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("html_url")
    val html_url: String? = null,

    @SerializedName("followers_url")
    val followers_url: String? = null,

    @SerializedName("following_url")
    val following_url: String? = null,

    @SerializedName("gists_url")
    val gists_url: String? = null,

    @SerializedName("starred_url")
    val starred_url: String? = null,

    @SerializedName("subscriptions_url")
    val subscriptions_url: String? = null,

    @SerializedName("organizations_url")
    val organizations_url: String? = null,

    @SerializedName("repos_url")
    val repos_url: String? = null,

    @SerializedName("events_url")
    val events_url: String? = null,

    @SerializedName("received_events_url")
    val received_events_url: String? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("site_admin")
    val site_admin: Boolean? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("company")
    val company: String? = null,

    @SerializedName("blog")
    val blog: String? = null,

    @SerializedName("location")
    val location: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("hireable")
    val hireable: String? = null,

    @SerializedName("bio")
    val bio: String? = null,

    @SerializedName("twitter_username")
    val twitter_username: String? = null,

    @SerializedName("public_repos")
    val public_repos: Int? = null,

    @SerializedName("site_admin")
    val followers: Int? = null,

    @SerializedName("site_admin")
    val following: Int? = null,

    @SerializedName("site_admin")
    val created_at: String? = null,

    @SerializedName("site_admin")
    val updated_at: String? = null

)