package eu.petrfaruzel.nba.data.api.services.players.models

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("id")
    val id: Long? = null,

    @SerializedName("abbreviation")
    val abbreviation: String? = null,

    @SerializedName("city")
    val city: String? = null,

    @SerializedName("conference")
    val conference: String? = null,

    @SerializedName("division")
    val division: String? = null,

    @SerializedName("full_name")
    val fullName: String? = null,

    @SerializedName("name")
    val name: String? = null,
)