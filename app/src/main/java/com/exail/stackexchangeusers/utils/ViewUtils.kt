package com.exail.stackexchangeusers.utils

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.BindingAdapter

fun View.hideKeyboard() {
    try {
        (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager)
            ?.hideSoftInputFromWindow(windowToken, 0)
    } catch (ex: Exception) {
        Log.e("View", "Unable to hide keyboard", ex)
    }
}

@BindingAdapter("is_gone")
fun View.isGone(isGone: Boolean = false) {
    visibility = if (isGone) View.GONE else View.VISIBLE
}

@BindingAdapter("visible_or_gone")
fun View.visibleOrGone(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}
