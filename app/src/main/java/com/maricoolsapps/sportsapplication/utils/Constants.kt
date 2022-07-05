package com.maricoolsapps.sportsapplication.utils

import com.maricoolsapps.sportsapplication.data.models.Cast
import com.maricoolsapps.sportsapplication.data.models.MovieListItemModel
import com.maricoolsapps.sportsapplication.data.models.Person

object Constants {

    const val API_VERSION: Int = 3
    const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w185"
    const val BASE_BACKDROP_URL = "https://image.tmdb.org/t/p/w780"
    const val BASE_PROFILE_URL = "https://image.tmdb.org/t/p/w185"
    const val BASE_YT_IMG_URL = "https://img.youtube.com/vi/"
    const val BASE_YT_WATCH_URL = "https://www.youtube.com/watch?v="
    const val BASE_API_URL = "https://api.themoviedb.org/"
    const val API_KEY = "980e65330752519ef6d7e220b6a67ea3"

    val HOME_ROUTE = "home_route"
    val MOVIES_ROUTE = "movies_route"
    val DETAILS_ROUTE = "details_route"
    val CAST_ROUTE = "cast_details"
    val TVSHOWS_ROUTE = "tvshows_route"
    val MOVIE_CATEGORY_GRID_LIST = "movie category grid list"
    val TV_LIST_DETAILS = "tv list details"

    const val POPULAR_MOVIES = "popular"
    const val IN_THEATRE_MOVIES = "now_playing"
    const val UPCOMING_MOVIES = "upcoming"
    const val ALL_MOVIES = "movie"
    const val TV_SHOWS = "tv"
}