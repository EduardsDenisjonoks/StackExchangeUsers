package com.exail.stackexchangeusers.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.epochTimeToFormattedString(
    simpleDateFormat: SimpleDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
): String {
    return try {
        simpleDateFormat.format(Date(this * 1000))
    } catch (ex: Exception) {
        this.toString()
    }
}