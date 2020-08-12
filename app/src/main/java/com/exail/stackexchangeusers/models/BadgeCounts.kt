package com.exail.stackexchangeusers.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BadgeCounts(
    @Expose
    @SerializedName("bronze")
    var bronze: Int = 0,
    @Expose
    @SerializedName("silver")
    var silver: Int = 0,
    @Expose
    @SerializedName("gold")
    var gold: Int = 0
) : Parcelable