package eu.petrfaruzel.nba.domain.features.players.models

import eu.petrfaruzel.nba.data.api.services.players.models.PlayerResult

data class PlayerResultDO(
    val players: List<PlayerDO> = emptyList(),
    val meta: MetaDataDO? = null
)

fun PlayerResult.toDO() = PlayerResultDO(
    players = this.players.map { it.toDO() },
    meta = this.meta?.toDO()
)