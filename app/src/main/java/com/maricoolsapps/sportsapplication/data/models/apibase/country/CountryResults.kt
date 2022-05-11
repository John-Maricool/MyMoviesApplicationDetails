package com.maricoolsapps.sportsapplication.data.models.apibase.country

import com.google.gson.annotations.SerializedName


class CountryResults(
    @SerializedName("response")
    val countryList: List<Country>
)