package com.exail.stackexchangeusers.core.network

import com.exail.stackexchangeusers.R
import retrofit2.Response
import java.lang.Exception
import java.net.UnknownHostException

interface AppErrorResolver {

    fun <T> resolveError(error: T): AppError
}

fun <T : AppErrorResolver> Array<T>.resolveError(error: Any): AppError {
    this.forEach {
        val resolvedError = it.resolveError(error)
        if (resolvedError !is DefaultError.Unknown) return resolvedError
    }
    return DefaultError.Unknown()
}

class NetworkErrorResolver : AppErrorResolver {

    override fun <T> resolveError(error: T): AppError = when (error) {
        is Response<*> -> resolveError(error as Response<*>)
        is Exception -> resolveError(error)
        is Throwable -> NetworkError.Error(throwable = error)
        is Int -> DefaultError.Unknown(errorResource = error)
        else -> DefaultError.Unknown(throwable = Throwable("Unable to resolve error"))
    }

    fun <T : Any> resolveError(response: Response<T>): AppError = when {
        response.code() == 401 -> NetworkError.AccessDenied()
        response.code() in 500..599 -> NetworkError.ServerUnavailable()
        else -> DefaultError.Unknown()
    }

    private fun resolveError(exception: Exception): AppError = when (exception) {
        is UnknownHostException -> NetworkError.Error(
            throwable = Throwable("Unable to resolve host"),
            errorResource = R.string.error_invalid_host
        )
        else -> DefaultError.Unknown()
    }
}
