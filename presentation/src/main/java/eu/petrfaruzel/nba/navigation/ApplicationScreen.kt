package eu.petrfaruzel.nba.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.petrfaruzel.nba.domain.features.players.models.PlayerDO
import eu.petrfaruzel.nba.domain.features.teams.models.TeamDO
import eu.petrfaruzel.nba.features.players.PlayerDetailScreen
import eu.petrfaruzel.nba.features.players.PlayersScreen
import eu.petrfaruzel.nba.features.teams.TeamDetailScreen
import eu.petrfaruzel.nba.shared.tryParseJson

/**
 * Primary composable that contains root navigation
 * of the application
 *
 * Routing is provided via destinations
 * @see Destination
 */
@Composable
fun ApplicationScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = PlayersScreenDestination.route
    ) {
        // Players screen
        composable(route = PlayersScreenDestination.route) {
            PlayersScreen(navController)
        }

        // Player Detail Screen
        composable(
            route = PlayerDetailScreenDestination.routeWithArgs,
            arguments = PlayerDetailScreenDestination.arguments
        ) {
            val jsonPlayer = it.arguments?.getString(PlayerDetailScreenDestination.playerArg)
            val player = tryParseJson<PlayerDO>(jsonPlayer)
            PlayerDetailScreen(
                navController,
                playerDetail = player
            )
        }

        // Team Detail Screen
        composable(
            route = TeamDetailScreenDestination.routeWithArgs,
            arguments = TeamDetailScreenDestination.arguments
        ) {
            val jsonTeam = it.arguments?.getString(TeamDetailScreenDestination.teamArg)
            val team = tryParseJson<TeamDO>(jsonTeam)
            TeamDetailScreen(teamDetail = team)
        }

    }
}