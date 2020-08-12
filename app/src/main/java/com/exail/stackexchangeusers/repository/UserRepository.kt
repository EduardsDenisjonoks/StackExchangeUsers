package com.exail.stackexchangeusers.repository

import com.exail.stackexchangeusers.core.network.ApiConfig
import com.exail.stackexchangeusers.core.network.ApiResult
import com.exail.stackexchangeusers.models.DataResponse
import com.exail.stackexchangeusers.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface UserRepository {

    suspend fun getUsers(page: Int, pageSize: Int, search: String?): ApiResult<DataResponse<User>>

    suspend fun getUser(vararg userIds: Int): ApiResult<List<User>>

}

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {


    override suspend fun getUsers(
        page: Int,
        pageSize: Int,
        search: String?
    ): ApiResult<DataResponse<User>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = userApi.getUsers(
                    page,
                    pageSize,
                    ApiConfig.ORDER,
                    ApiConfig.SORT,
                    search ?: "",
                    ApiConfig.SITE
                )
                if (response.isSuccessful) {
                    ApiResult.success(response.body() ?: DataResponse(emptyList(), false))
                } else {
                    ApiResult.error(response)
                }
            } catch (ex: Exception) {
                ApiResult.error(ex)
            }
        }
    }

    override suspend fun getUser(vararg userIds: Int): ApiResult<List<User>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = userApi.getUser(
                    userIds.joinToString(","),
                    ApiConfig.ORDER,
                    ApiConfig.SORT,
                    ApiConfig.SITE
                )
                if (response.isSuccessful) {
                    ApiResult.success(response.body()?.items ?: emptyList())
                } else {
                    ApiResult.error(response)
                }
            } catch (ex: Exception) {
                ApiResult.error(ex)
            }
        }
    }

}