package com.exail.stackexchangeusers.models

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @Expose
    @SerializedName("user_id")
    var userId: Int,
    @Expose
    @SerializedName("display_name")
    var name: String,
    @Expose
    @SerializedName("profile_image")
    var profileImage: String,
    @Expose
    @SerializedName("reputation")
    var reputation: Int,
    @Expose
    @SerializedName("badge_counts")
    var badgeCounts: BadgeCounts,
    @Expose
    @SerializedName("location")
    var location: String?,
    @Expose
    @SerializedName("age")
    var age: Int?,
    @Expose
    @SerializedName("creation_date")
    var creationDate: Long //epoch time
) : Parcelable

object UserComparator : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}