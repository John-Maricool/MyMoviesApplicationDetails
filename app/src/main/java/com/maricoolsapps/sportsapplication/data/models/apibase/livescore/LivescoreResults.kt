package com.maricoolsapps.sportsapplication.data.models.apibase.livescore

import com.google.gson.annotations.SerializedName

class LivescoreResults(
    @SerializedName("result")
    val result: List<LsResult>
)

data class LsResult(
    val mainData: LiveDatas,
    @SerializedName("goalscorers")
    val goalScorers: List<GoalScored>,
    @SerializedName("cards")
    val cards: List<Cards>,
    @SerializedName("substitutes")
    val substitutes: List<Substitutes>,
    @SerializedName("lineups")
    val lineUp: LineUp,
    @SerializedName("statistics")
    val matchStats: Stats
)

