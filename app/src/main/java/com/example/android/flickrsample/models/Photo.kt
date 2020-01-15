package com.example.android.flickrsample.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    val identifier: Int,
    val farm: Int,
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val title: String,

    @Json(name = "isfamily")
    val isFamily: Int,

    @Json(name = "isfriend")
    val isFriend: Int,

    @Json(name = "ispublic")
    val isPublic: Int

) : Parcelable