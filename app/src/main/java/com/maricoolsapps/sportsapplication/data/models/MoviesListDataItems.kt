package com.maricoolsapps.sportsapplication.data.models

import com.google.gson.annotations.SerializedName

class MoviesListItem(
    val id: Long,
    @SerializedName("title")
    val title: String?,
    @SerializedName("poster_path")
    val image: String?
)

class MoviesResult(
    @SerializedName("results")
    val results: List<MoviesListItem>
){
    constructor():this(listOf())
}