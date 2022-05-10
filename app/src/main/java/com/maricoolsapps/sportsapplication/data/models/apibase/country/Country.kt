package com.maricoolsapps.sportsapplication.data.models.apibase.country

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("country_key")
    val id: String,
    @SerializedName("country_name")
    val countryName: String,
    @SerializedName("country_logo")
    val countryLogo: String
)