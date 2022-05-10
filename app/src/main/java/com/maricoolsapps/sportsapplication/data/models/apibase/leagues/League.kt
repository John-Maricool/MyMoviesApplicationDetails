package com.maricoolsapps.sportsapplication.data.models.apibase.leagues

import com.google.gson.annotations.SerializedName

data class League(
    @SerializedName("league_key")
    val id: String,
    @SerializedName("league_name")
    val leagueName: String,
    @SerializedName("country_key")
    val countryId: String,
    @SerializedName("league_logo")
    val leagueLogo: String
)