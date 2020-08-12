package com.exail.stackexchangeusers.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataResponse<T : Parcelable>(
    @Expose
    @SerializedName("items")
    var items: List<T> = emptyList(),
    @Expose
    @SerializedName("has_more")
    var hasMore: Boolean = false
) : Parcelable