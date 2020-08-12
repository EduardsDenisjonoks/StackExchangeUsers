package com.exail.stackexchangeusers.core.network

import com.exail.stackexchangeusers.R

interface AppError {

    val errorResource: Int

    val errorString: String

    val throwable: Throwable?

    fun getErrorRes(): Int = errorResource

    fun getErrorMessage(): String = errorString

    fun getError(): Throwable = throwable ?: Throwable("Unknown error")
}

sealed class DefaultError : AppError {

    data class Unknown(
        override val throwable: Throwable? = null,
        override val errorString: String = throwable?.localizedMessage ?: "",
        override val errorResource: Int = R.string.error_something_went_wrong
    ) : DefaultError()

}

sealed class NetworkError : AppError {

    data class AccessDenied(
        override val throwable: Throwable? = null,
        override val errorString: String = throwable?.localizedMessage ?: "",
        override val errorResource: Int = R.string.error_access_denies
    ) : NetworkError()


    data class ServerUnavailable(
        override val throwable: Throwable? = null,
        override val errorString: String = throwable?.localizedMessage ?: "",
        override val errorResource: Int = R.string.error_service_unavailable
    ) : NetworkError()

    data class Error(
        override val throwable: Throwable? = null,
        override val errorString: String = throwable?.localizedMessage ?: "",
        override val errorResource: Int = R.string.error_something_went_wrong
    ) : NetworkError()
}