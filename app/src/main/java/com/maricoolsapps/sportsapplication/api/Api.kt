package com.maricoolsapps.sportsapplication.api

import com.maricoolsapps.sportsapplication.data.models.*
import com.maricoolsapps.sportsapplication.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") key: String = Constants.API_KEY,
        @Query("page") page: Int
    ): MoviesResult

    @GET("movie/now_playing")
    suspend fun getInTheatreMovies(
        @Query("api_key") key: String = Constants.API_KEY,
        @Query("page") page: Int
    ): MoviesResult


    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") key: String = Constants.API_KEY,
        @Query("page") page: Int
    ): MoviesResult


    @GET("discover/movie")
    suspend fun getAllMovies(
        @Query("api_key") key: String = Constants.API_KEY,
        @Query("page") page: Int
    ): MoviesResult


    @GET("discover/tv")
    suspend fun getTvShows(
        @Query("api_key") key: String = Constants.API_KEY,
        @Query("page") page: Int
    ): TvResult

    @GET("movie/{id}")
    suspend fun fetchDetails(
        @Path("id") id: Int,
        @Query("api_key") key: String = Constants.API_KEY
    ): Movie

    @GET("movie/{id}/credits")
    suspend fun fetchCredits(
        @Path("id") id: Int,
        @Query("api_key") key: String = Constants.API_KEY
    ): CastResults

    @GET("movie/{id}/videos")
    suspend fun fetchVideos(
        @Path("id") id: Int,
        @Query("api_key") key: String = Constants.API_KEY
    ): VideoResult

}




