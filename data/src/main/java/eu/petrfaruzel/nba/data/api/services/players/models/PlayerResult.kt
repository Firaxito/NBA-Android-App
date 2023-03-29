package eu.petrfaruzel.nba.data.api.services.players.models

import com.google.gson.annotations.SerializedName

data class PlayerResult(
    @SerializedName("data")
    val players: List<Player> = emptyList(),
    @SerializedName("meta")
    val meta: MetaData? = null,
)