package com.maricoolsapps.sportsapplication.data.models

import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("profile_path")
    var profilePath: String?,

    @SerializedName("known_for_department")
    var knownFor: String?
)

data class CastResults(
    @SerializedName("cast")
    val casts: List<Cast>,
)