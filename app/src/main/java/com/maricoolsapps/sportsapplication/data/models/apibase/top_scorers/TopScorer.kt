package com.maricoolsapps.sportsapplication.data.models.apibase.top_scorers

import com.google.gson.annotations.SerializedName

data class TopScorer(
    @SerializedName("player_place")
    val playerPosition: String,
    @SerializedName("player_name")
    val playerName: String,
    @SerializedName("player_key")
    val playerKey: String,
    @SerializedName("team_name")
    val teamName: String,
    @SerializedName("team_key")
    val teamKey: String,
    @SerializedName("goals")
    val goals: String,
    @SerializedName("assists")
    val assists: String,
    @SerializedName("penalty_goals")
    val penaltyGoals: String,
)