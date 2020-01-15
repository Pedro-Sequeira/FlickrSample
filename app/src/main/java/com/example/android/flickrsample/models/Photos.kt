package com.example.android.flickrsample.models

import com.squareup.moshi.Json

data class Photos(

    val page: Int,

    val pages: Int,

    @Json(name = "perpage")
    val perPage: Int,

    val photo: List<Photo>,

    val total: String

)