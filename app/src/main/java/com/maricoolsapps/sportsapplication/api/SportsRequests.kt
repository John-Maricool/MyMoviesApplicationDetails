package com.maricoolsapps.sportsapplication.api

import com.maricoolsapps.sportsapplication.data.models.apibase.country.CountryResults
import com.maricoolsapps.sportsapplication.utils.Constants
import retrofit2.http.GET

interface SportsRequests {

    companion object {
        const val BASE_URL = "https://apiv2.allsportsapi.com/"
    }

    @GET("/football?met=Countries&APIkey=${Constants.apiKey}")
    suspend fun getAllCountries(): CountryResults
}