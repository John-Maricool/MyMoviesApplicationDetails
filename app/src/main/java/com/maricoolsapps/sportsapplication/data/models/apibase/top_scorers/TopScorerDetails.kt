package com.maricoolsapps.sportsapplication.data.models.apibase.top_scorers

import com.google.gson.annotations.SerializedName

data class TopScorerDetails(
    @SerializedName("result")
    val result: List<TopScorer>
)