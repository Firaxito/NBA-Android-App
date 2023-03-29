@file:OptIn(ExperimentalComposeUiApi::class)

package eu.petrfaruzel.nba.features.players

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import eu.petrfaruzel.nba.R
import eu.petrfaruzel.nba.core.compose.AttributeRow
import eu.petrfaruzel.nba.core.compose.AttributeRowData
import eu.petrfaruzel.nba.core.compose.NoContentScreen
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
            getStringHeight(
                player.heightFeet,
                player.heightInches
            )
        ),
        AttributeRowData(
            stringResource(id = R.string.player_weight),
            getStringWeight(weightPounds = player.weightPounds)
        ),
    )

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.player_detail)
            )
        }
        Divider(modifier = Modifier.height(2.dp))

        for (attrib in attributes) {
            AttributeRow(
                attributes = attrib
            )
        }

    }
}

// Support functions

@Composable
private fun getStringWeight(weightPounds: Long?): String? {
    return if (weightPounds != null) "$weightPounds ${
        pluralStringResource(
            id = R.plurals.unit_pound,
            count = weightPounds.toInt()
        )
    }" else null
}

private fun getStringHeight(heightFeet: Long?, heightInches: Long?): String? {
    return if (heightFeet != null)
        "${heightFeet}\" ${heightInches ?: 0}\'"
    else null
}


@Preview(showBackground = true)
@Composable
private fun PlayerDetailScreenContentPreview() {
    PlayerDetailScreenContent(
        player = PlayerDO(),
        navController = rememberNavController()
    )
}