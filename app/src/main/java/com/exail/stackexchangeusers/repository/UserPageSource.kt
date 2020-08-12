package com.exail.stackexchangeusers.repository

import androidx.paging.PagingSource
import com.exail.stackexchangeusers.core.network.ApiResult
import com.exail.stackexchangeusers.models.User
import java.lang.Exception
import java.lang.NullPointerException

class UserPageSource constructor(
    private val userRepository: UserRepository,
    private val searchQuery: String?
) :
    PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        try {
            val nextPage = params.key ?: 1
            return when (val response =
                userRepository.getUsers(nextPage, params.loadSize, searchQuery)) {
                is ApiResult.Success -> {
                    LoadResult.Page(
                        data = response.data.items,
                        prevKey = if (nextPage == 1) null else nextPage - 1,
                        nextKey = if (response.data.hasMore) nextPage + 1 else null
                    )
                }
                else -> throw NullPointerException()
            }
        } catch (ex: Exception) {
            return LoadResult.Error(ex)
        }
    }

}