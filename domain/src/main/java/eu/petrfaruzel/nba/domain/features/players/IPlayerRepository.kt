package eu.petrfaruzel.nba.domain.features.players

import eu.petrfaruzel.nba.domain.features.players.models.PlayerResultDO
import eu.petrfaruzel.nba.shared.ResultState

interface IPlayerRepository {
    suspend fun getPlayers(page: Int, count: Int): ResultState<PlayerResultDO>
}