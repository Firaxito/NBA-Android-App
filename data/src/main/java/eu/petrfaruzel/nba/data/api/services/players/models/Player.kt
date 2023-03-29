package eu.petrfaruzel.nba.data.api.services.players.models

import com.google.gson.annotations.SerializedName


data class Player(
    @SerializedName("first_name")
    val firstName: String? = null,

    @SerializedName("last_name")
    val lastName: String? = null,

    @SerializedName("position")
    val position: String? = null,

    @SerializedName("height_feet")
    val heightFeet: Long? = null,

    @SerializedName("height_inches")
    val heightInches: Long? = null,

    @SerializedName("weight_pounds")
    val weightPounds: Long? = null,

    @SerializedName("team")
    val team: Team? = null,
)