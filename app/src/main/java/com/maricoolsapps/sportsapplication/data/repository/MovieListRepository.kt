package com.maricoolsapps.sportsapplication.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.maricoolsapps.sportsapplication.api.Api
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

    fun getTvList() =  Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 200,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { TvListSource(api) }
    ).flow
}