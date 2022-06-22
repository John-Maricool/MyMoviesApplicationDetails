package com.maricoolsapps.sportsapplication.utils

import com.maricoolsapps.sportsapplication.data.models.Cast
import com.maricoolsapps.sportsapplication.data.models.MovieListItemModel

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
    val TVSHOWS_ROUTE = "tvshows_route"
    val MOVIE_CATEGORY_GRID_LIST = "movie category grid list"

    val POPULAR_MOVIES = 0
    val IN_THEATRE_MOVIES = 1
    val UPCOMING_MOVIES = 2
    val ALL_MOVIES = 3
    val TV_SHOWS = 4

}

fun castsToMovieListItem(cast: Cast): MovieListItemModel {
    return MovieListItemModel(cast.profilePath, cast.id.toLong(), cast.name)
}