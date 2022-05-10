package com.maricoolsapps.sportsapplication.data.models.apibase.livescore

import com.google.gson.annotations.SerializedName

data class GoalScored(
    @SerializedName("time")
    val goalTime: String,
    @SerializedName("home_scorer")
    val homeScorer: String,
    @SerializedName("score")
    val score: String,
    @SerializedName("away_scorer")
    val awayScorer: String
)

data class Substitutes(
    @SerializedName("time")
    val time: String,
    @SerializedName("score")
    val sub: String,
    @SerializedName("away_scorer")
    val awayScorer: List<String>,
    @SerializedName("home_scorer")
    val homeSub: HomeSub
)

data class Cards(
    @SerializedName("time")
    val time: String,
    @SerializedName("home_fault")
    val homeFault: String,
    @SerializedName("card")
    val cardType: String,
    @SerializedName("away_fault")
    val awayFault: String
)

data class Stats(
    val type: String,
    val home: String,
    val away: String
)

data class LineUp(
    @SerializedName("home_team")
    val hone: HomeTeam,
    @SerializedName("away_team")
    val away: AwayTeam,
)


data class LiveDatas(
    @SerializedName("event_key")
    val id: String,
    @SerializedName("event_date")
    val matchDate: String,
    @SerializedName("event_time")
    val matchTime: String,
    @SerializedName("event_home_team")
    val homeTeam: String,
    @SerializedName("home_team_key")
    val homeKey: String,
    @SerializedName("event_away_team")
    val awayTeam: String,
    @SerializedName("away_team_key")
    val awayKey: String,
    @SerializedName("event_halftime_result")
    val halfTimeResult: String,
    @SerializedName("event_final_result")
    val finalTimeResult: String,
    @SerializedName("event_ft_result")
    val fullTimeResult: String,
    @SerializedName("event_penalty_result")
    val penResult: String,
    @SerializedName("country_name")
    val countryName: String,
    @SerializedName("league_name")
    val leagueName: String,
    @SerializedName("league_key")
    val leagueKey: String,
    @SerializedName("league_round")
    val leagueRound: String,
    @SerializedName("league_season")
    val leagueSeason: String,
    @SerializedName("event_live")
    val eventLive: String,
    @SerializedName("event_stadium")
    val eventStadium: String,
    @SerializedName("event_referee")
    val eventReferee: String,
    @SerializedName("event_country_key")
    val eventCountryKey: String,
    @SerializedName("league_logo")
    val leagueLogo: String,
    @SerializedName("country_logo")
    val countryLogo: String,
    @SerializedName("event_home_formation")
    val homeFormation: String,
    @SerializedName("event_away_formation")
    val awayFormation: String,
    @SerializedName("stage_name")
    val stageName: String
)


data class HomeTeam(
    @SerializedName("starting_lineups")
    val lineUp: List<LineUps>,
    @SerializedName("substitutes")
    val subs: List<LineUps>,
    val coaches: List<Coaches>
)

data class AwayTeam(
    @SerializedName("starting_lineups")
    val lineUp: List<LineUps>,
    @SerializedName("substitutes")
    val subs: List<LineUps>,
    val coaches: List<Coaches>
)

data class Coaches(
    val coach: String,
    @SerializedName("coache_country")
    val coachCountry: String
)

data class LineUps(
    val player: String,
    @SerializedName("player_number")
    val playerNumber: String,
    @SerializedName("player_country")
    val playerCountry: String
)

data class HomeSub(
    @SerializedName("in")
    val subIn: String,
    @SerializedName("out")
    val subOut: String
)