package com.maricoolsapps.sportsapplication.data.models.apibase.team

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("team_key")
    val id: String,
    @SerializedName("team_name")
    val teamName: String,
    @SerializedName("team_logo")
    val teamLogo: String
)