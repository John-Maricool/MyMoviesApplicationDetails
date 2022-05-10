package com.maricoolsapps.sportsapplication.data.models.apibase.leagues

import com.google.gson.annotations.SerializedName

data class LeagueResults(
    @SerializedName("result")
    val leagueList: List<League>
)