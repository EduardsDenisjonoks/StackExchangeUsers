package com.exail.stackexchangeusers.utils

import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("image_url")
fun AppCompatImageView.loadImage(url: String?) {
    try {
        when {
            url.isNullOrBlank() -> Glide.with(this).clear(this)
            else -> Glide.with(this).load(url).into(this)
        }
    } catch (ex: Exception) {
        Log.e("AppCompatImageView", "Unable to load image url: $url")
    }
}