package com.exail.stackexchangeusers.repository

import com.exail.stackexchangeusers.models.DataResponse
import com.exail.stackexchangeusers.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int,
        @Query("order") order: String,
        @Query("sort") sort: String,
        @Query("inname") search: String,
        @Query("site") site: String
    ): Response<DataResponse<User>>

    @GET("users/{ids}")
    suspend fun getUser(
        @Path("ids") userIds: String,
        @Query("order") order: String,
        @Query("sort") sort: String,
        @Query("site") site: String
    ): Response<DataResponse<User>>
}