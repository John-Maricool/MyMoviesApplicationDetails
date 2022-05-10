package com.maricoolsapps.sportsapplication.data.models.apibase.country

import com.google.gson.annotations.SerializedName


class CountryResults(
    @SerializedName("result")
    val countryList: List<Country>
)