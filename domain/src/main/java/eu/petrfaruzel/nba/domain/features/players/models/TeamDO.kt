package eu.petrfaruzel.nba.domain.features.players.models

import eu.petrfaruzel.nba.data.api.services.players.models.Team

data class TeamDO(
    val id: Long? = null,
    val abbreviation: String? = null,
    val city: String? = null,
    val conference: String? = null,
    val division: String? = null,
    val fullName: String? = null,
    val name: String? = null,
)

fun Team.toDO() = TeamDO(
    id = this.id,
    abbreviation = this.abbreviation,
    city = this.city,
    conference = this.conference,
    division = this.division,
    fullName = this.fullName,
    name = this.name
)