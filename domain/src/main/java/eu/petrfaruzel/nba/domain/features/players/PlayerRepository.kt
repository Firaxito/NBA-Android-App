package eu.petrfaruzel.nba.domain.features.players

import androidx.annotation.Size
import eu.petrfaruzel.nba.data.api.services.players.apis.PlayerApi
import eu.petrfaruzel.nba.domain.features.players.models.PlayerResultDO
import eu.petrfaruzel.nba.domain.features.players.models.PlayerDO
import eu.petrfaruzel.nba.domain.features.players.models.toDO
import eu.petrfaruzel.nba.shared.ResultState

/**
 * Repository for NBA players retrieval
 *
 * Teams are currently downloaded together with each player
 * @see PlayerDO
 */
internal class PlayerRepository(
    private val playerApi: PlayerApi
) : IPlayerRepository {

    /**
     * Retrieve list of players with specified pagination
     * @param page Page to be downloaded
     * @param count Number of players to be loaded (limited in range 0 - 100)
     */
    override suspend fun getPlayers(page: Int, @Size(min = 0, max = 100) count: Int): ResultState<PlayerResultDO> {
        val response = try {
            playerApi.getPlayers(page = page, perPage = count)
        } catch (e: Exception) {
            return ResultState.Error(e.message)
        }

        val responseBody = response.body()
        if (response.isSuccessful && responseBody != null) {
            return ResultState.Success(responseBody.toDO())
        }
        return ResultState.Error(response.message())
    }

}