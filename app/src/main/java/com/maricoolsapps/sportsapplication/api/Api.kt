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

    @GET("movie/{type}?page=1")
    suspend fun getMovieFromTypeSinglePage(
        @Path("type") type: String,
        @Query("api_key") key: String = Constants.API_KEY
    ): QueryResult<MoviesListItem>

    @GET("movie/{type}")
    suspend fun getMovieCategory(
        @Path("type") type: String,
        @Query("api_key") key: String = Constants.API_KEY,
        @Query("page") page: Int
    ): QueryResult<MoviesListItem>

    @GET("discover/{movie}")
    suspend fun getAllMoviesAndTv(
        @Path("movie") type: String,
        @Query("api_key") key: String = Constants.API_KEY,
        @Query("page") page: Int
    ): QueryResult<MoviesListItem>

    @GET("movie/{id}")
    suspend fun fetchDetails(
        @Path("id") id: Int,
        @Query("api_key") key: String = Constants.API_KEY
    ): Movie

    @GET("tv/{id}")
    suspend fun fetchTvDetails(
        @Path("id") id: Int,
        @Query("api_key") key: String = Constants.API_KEY
    ): TvShowDetails

    @GET("tv/{id}/credits")
    suspend fun fetchTvCredits(
        @Path("id") id: Int,
        @Query("api_key") key: String = Constants.API_KEY
    ): CastResults

    @GET("tv/{id}/videos")
    suspend fun fetchTvVideos(
        @Path("id") id: Int,
        @Query("api_key") key: String = Constants.API_KEY
    ): VideoResult

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

    @GET("person/{id}/images")
    suspend fun fetchCastImages(
        @Path("id") id: Int, @Query("api_key") key: String = Constants.API_KEY
    ): PersonImagesResponse

    @GET("person/{id}/combined_credits")
    suspend fun fetchCrewCredits(
        @Path("id") id: Int,
        @Query("api_key") key: String = Constants.API_KEY
    ): PersonCreditsResponse

    @GET("person/{id}")
    suspend fun fetchPersonDetails(
        @Path("id") id: Int, @Query("api_key") key: String = Constants.API_KEY
    ): Person


}




