package com.maricoolsapps.sportsapplication.api

import com.maricoolsapps.sportsapplication.data.models.apibase.country.CountryResults
import com.maricoolsapps.sportsapplication.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Headers

interface SportsRequests {

    companion object {
       // const val BASE_URL = "https://api-football-v1.p.rapidapi.com/v3/"
        const val BASE_URL = "https://v3.football.api-sports.io/"
    }

    @Headers("x-rapidapi-key:${Constants.apiKey}", "x-rapidapi-host: v3.football.api-sports.io")
    @GET("countries")
    suspend fun getAllCountries(): CountryResults
}