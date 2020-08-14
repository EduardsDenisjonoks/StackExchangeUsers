package com.exail.stackexchangeusers.utils

fun Any?.nonNullOrBlank(emptySymbol: String = "-"): String {
    return try {
        return when (this) {
            is String? -> if (this.isNullOrBlank()) emptySymbol else this as String
            is Int? -> this?.toString() ?: emptySymbol
            else -> this?.toString() ?: emptySymbol
        }
    } catch (ex: Exception) {
        emptySymbol
    }
}