package eu.petrfaruzel.nba.domain.features.players.models

import eu.petrfaruzel.nba.data.api.services.players.models.Player

data class PlayerDO(
    val firstName: String? = null,
    val lastName: String? = null,

    val position: String? = null,
    val heightFeet: Long? = null,
    val heightInches: Long? = null,
    val weightPounds: Long? = null,

    val team: TeamDO? = null
)

fun Player.toDO() = PlayerDO(
    firstName = this.firstName,
    lastName = this.lastName,
    position = this.position,
    heightFeet = this.heightFeet,
    heightInches = this.heightInches,
    weightPounds = this.weightPounds,
    team = this.team?.toDO()
)