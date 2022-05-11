package com.maricoolsapps.sportsapplication.data.models.apibase.country

import com.google.gson.annotations.SerializedName

data class Country(
     val code: String,
    val name: String,
    @SerializedName("flag")
    val countryLogo: String
)