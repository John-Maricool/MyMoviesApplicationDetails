package com.maricoolsapps.sportsapplication.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.maricoolsapps.sportsapplication.api.Api
import com.maricoolsapps.sportsapplication.data.models.*
import com.maricoolsapps.sportsapplication.data.source.MovieListSource
import com.maricoolsapps.sportsapplication.data.source.TvListSource
import javax.inject.Inject

class MovieListRepository
@Inject constructor(
    private val api: Api
) {

    fun getMovieList(type: Int) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 200,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MovieListSource(type, api) }
        ).flow

    fun getTvList() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 200,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { TvListSource(api) }
    ).flow

    suspend fun getSingleMovieId(): Movie {
        val id = api.getPopularMovies(page = 1).results.first().id
        return api.fetchDetails(id.toInt())
    }

    suspend fun getMovie(id: Long): Movie {
        return api.fetchDetails(id.toInt())
    }

    suspend fun getCredits(id: Long): CastResults {
        return api.fetchCredits(id = id.toInt())
    }

    suspend fun getVideos(id: Long): List<Video>? {
        return api.fetchVideos(id = id.toInt()).videos
    }

    suspend fun getPersonDetails(id: Long): Person{
        return api.fetchPersonDetails(id = id.toInt())
    }

    suspend fun getPersonPictures(id: Long): List<Image>?{
        return api.fetchCastImages(id = id.toInt()).results
    }

    suspend fun getPersonFeaturedMovies(id: Long): List<Credit>?{
        return api.fetchCrewCredits(id = id.toInt()).results
    }
}

