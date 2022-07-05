package com.maricoolsapps.sportsapplication.data.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.maricoolsapps.sportsapplication.api.Api
import com.maricoolsapps.sportsapplication.data.models.*
import com.maricoolsapps.sportsapplication.data.paging_source.AllMoviesAndTvSource
import com.maricoolsapps.sportsapplication.data.paging_source.MovieListSource
import com.maricoolsapps.sportsapplication.utils.checkForInternet
import com.maricoolsapps.sportsapplication.utils.mapAllToDataModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MovieListRepository
@Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: Api
) {

    fun getMovieList(type: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 200,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MovieListSource(api, type) }
        ).flow

    fun getAllMovieAndTvList(type: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 200,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { AllMoviesAndTvSource(api, type) }
        ).flow

    suspend fun getSingleMovieId(): Movie {
        val id = api.getMovieFromTypeSinglePage(type = "popular").results.first().id
        return api.fetchDetails(id.toInt())
    }

    suspend fun getMovieFromTypeSinglePage(type: String): List<MovieListItemModel> {
        val movies = api.getMovieFromTypeSinglePage(type = type).results
        return mapAllToDataModel(movies)
    }

    fun isConnected(): Boolean = context.checkForInternet()

    suspend fun getMovie(id: Long): Movie {
        return api.fetchDetails(id.toInt())
    }

    suspend fun getTvDetails(id: Long): TvShowDetails {
        return api.fetchTvDetails(id.toInt())
    }

    suspend fun getTvCredits(id: Long): CastResults {
        return api.fetchTvCredits(id = id.toInt())
    }

    suspend fun getCredits(id: Long): CastResults {
        return api.fetchCredits(id = id.toInt())
    }

    suspend fun getVideos(id: Long): List<Video>? {
        return api.fetchVideos(id = id.toInt()).videos
    }

    suspend fun getTvVideos(id: Long): List<Video>? {
        return api.fetchTvVideos(id = id.toInt()).videos
    }

    suspend fun getPersonDetails(id: Long): Person {
        return api.fetchPersonDetails(id = id.toInt())
    }

    suspend fun getPersonPictures(id: Long): List<Image>? {
        return api.fetchCastImages(id = id.toInt()).results
    }

    suspend fun getPersonFeaturedMovies(id: Long): List<Credit>? {
        return api.fetchCrewCredits(id = id.toInt()).results
    }
}

