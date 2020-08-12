package com.exail.stackexchangeusers.core.network

sealed class ApiResult<out T> {

    data class Success<out T>(val data: T) : ApiResult<T>()
    object Empty : ApiResult<Nothing>()
    data class Error(val appError: AppError) : ApiResult<Nothing>()

    companion object {
        fun <T> success(data: T): ApiResult<T> = Success(data)
        fun empty() = Empty
        fun error(
            errorBody: Any,
            vararg errorResolvers: AppErrorResolver = arrayOf(NetworkErrorResolver())
        ): ApiResult<Nothing> = Error(errorResolvers.resolveError(errorBody))
    }
}