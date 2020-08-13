package com.exail.stackexchangeusers.utils

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hideKeyboard() {
    try {
        (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager)
            ?.hideSoftInputFromWindow(windowToken, 0)
    } catch (ex: Exception) {
        Log.e("View", "Unable to hide keyboard", ex)
    }
}