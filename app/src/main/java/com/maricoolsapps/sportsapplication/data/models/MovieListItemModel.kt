package com.maricoolsapps.sportsapplication.data.models

class MovieListItemModel(
    val image: String?,
    val id: Long,
    val title: String?
){
    constructor(): this(null, 0, null)
}
