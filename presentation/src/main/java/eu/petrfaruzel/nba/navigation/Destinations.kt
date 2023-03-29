package eu.petrfaruzel.nba.navigation

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import eu.petrfaruzel.nba.domain.features.players.models.PlayerDO
import eu.petrfaruzel.nba.domain.features.teams.models.TeamDO
import eu.petrfaruzel.nba.shared.toJson

object PlayersScreenDestination : Destination {
    override val route: String = "players"
}

/**
 * Player's detail screen destination that requires JSON parsed
 * PlayerDO object in route for proper function
 * @see PlayerDO
 */
object PlayerDetailScreenDestination : Destination {
    override val route: String = "player_detail"
    const val playerArg = "player"
    val routeWithArgs = "$route/{$playerArg}"
    val arguments = listOf(
        navArgument(playerArg) { type = NavType.StringType }
    )
}

fun NavHostController.navigateToPlayerDetail(player: PlayerDO) {
    this.navigate("${PlayerDetailScreenDestination.route}/${player.toJson()}")
}

/**
 * Team's detail screen destination that requires JSON parsed
 * TeamDO object in route for proper function
 * @see TeamDO
 */
object TeamDetailScreenDestination : Destination {
    override val route: String = "team_detail"
    const val teamArg = "team"
    val routeWithArgs = "$route/{$teamArg}"
    val arguments = listOf(
        navArgument(teamArg) { type = NavType.StringType }
    )
}

fun NavHostController.navigateToTeamDetail(team: TeamDO) {
    this.navigate("${TeamDetailScreenDestination.route}/${team.toJson()}")
}