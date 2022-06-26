package com.maricoolsapps.sportsapplication.data.models

import com.google.gson.annotations.SerializedName


data class Image(
    @SerializedName("file_path")
    var filePath: String,
)

data class PersonImagesResponse(
    @SerializedName("profiles")
    var results: List<Image>?
)


data class PersonCreditsResponse(
    @SerializedName("cast")
    var results: List<Credit>?
)


data class Credit(
    @SerializedName("id")
    var id: Int,

    @SerializedName("media_type")
    var mediaType: String,

    @SerializedName("poster_path")
    var posterPath: String?,

    @SerializedName("original_title", alternate = ["original_name"])
    var title: String
)