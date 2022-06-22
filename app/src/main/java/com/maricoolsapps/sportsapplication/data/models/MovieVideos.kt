package com.maricoolsapps.sportsapplication.data.models

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("id")
    var id: String,

    @SerializedName("key")
    var key: String,

    @SerializedName("name")
    var name: String,
)


data class VideoResult(
    @SerializedName("results")
    val videos: List<Video>,
)