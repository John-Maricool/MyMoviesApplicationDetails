package com.maricoolsapps.sportsapplication.data.models

import com.google.gson.annotations.SerializedName

class MoviesListItem(
    val id: Long,
    @SerializedName("title")
    val title: String?,
    @SerializedName("poster_path")
    val image: String?
)

open class QueryResult<T>(
    @SerializedName("results")
    val results: List<T>
) {
    constructor() : this(listOf())
}