package com.maricoolsapps.sportsapplication.data.models.apibase.team

import com.google.gson.annotations.SerializedName

data class TeamDetails(
    val team: Team,
    @SerializedName("players")
    val teamPlayers: List<TeamPlayer>
)