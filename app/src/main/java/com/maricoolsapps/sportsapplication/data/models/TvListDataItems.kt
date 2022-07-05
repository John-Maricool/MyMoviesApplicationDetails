package com.maricoolsapps.sportsapplication.data.models

import com.google.gson.annotations.SerializedName

class TvListItem(
    val id: Long,
    @SerializedName("name")
    val title: String?,
    @SerializedName("poster_path")
    val image: String?
)
