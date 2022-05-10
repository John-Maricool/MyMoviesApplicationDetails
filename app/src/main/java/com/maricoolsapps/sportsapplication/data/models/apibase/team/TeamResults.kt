package com.maricoolsapps.sportsapplication.data.models.apibase.team

import com.google.gson.annotations.SerializedName

data class TeamResults(
    @SerializedName("results")
    val result: List<TeamDetails>
)