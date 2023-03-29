package eu.petrfaruzel.nba.features.players

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import eu.petrfaruzel.nba.R
import eu.petrfaruzel.nba.core.compose.AttributeRow
import eu.petrfaruzel.nba.core.compose.AttributeRowData
import eu.petrfaruzel.nba.core.compose.NoContentScreen
import eu.petrfaruzel.nba.core.compose.TopBar
import eu.petrfaruzel.nba.domain.features.players.models.PlayerDO
import eu.petrfaruzel.nba.navigation.navigateToTeamDetail

@Composable
fun PlayerDetailScreen(
    navController: NavHostController = rememberNavController(),
    playerDetail: PlayerDO?,
) {
    if (playerDetail != null) {
        PlayerDetailScreenContent(
            navController,
            player = playerDetail
        )
    } else {
        NoContentScreen()
    }
}


@Composable
private fun PlayerDetailScreenContent(
    navController: NavHostController,
    player: PlayerDO
) {

    val context = LocalContext.current

    val attributes = listOf(
        AttributeRowData(
            stringResource(id = R.string.player_first_name),
            player.firstName
        ),
        AttributeRowData(
            stringResource(id = R.string.player_last_name),
            player.lastName
        ),
        AttributeRowData(
            stringResource(id = R.string.player_team),
            player.team?.fullName,
            highlighted = player.team != null
        ) {
            val team = player.team
            if (team != null) navController.navigateToTeamDetail(team)
        },
        AttributeRowData(
            stringResource(id = R.string.player_position),
            player.position
        ),
        AttributeRowData(
            stringResource(id = R.string.player_height),
            player.getStringHeight()
        ),
        AttributeRowData(
            stringResource(id = R.string.player_weight),
            player.getStringWeight(
                context,
                R.plurals.unit_pound
            )
        ),
    )

    Column {
        TopBar(
            navController = navController,
            title = stringResource(id = R.string.player_detail),
            backNavigationEnabled = true
        )

        for (attrib in attributes) {
            AttributeRow(
                attributes = attrib
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun PlayerDetailScreenContentPreview() {
    PlayerDetailScreenContent(
        player = PlayerDO(),
        navController = rememberNavController()
    )
}