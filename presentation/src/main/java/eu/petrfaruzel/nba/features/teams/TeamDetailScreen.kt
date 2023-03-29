package eu.petrfaruzel.nba.features.teams

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import eu.petrfaruzel.nba.R
import eu.petrfaruzel.nba.core.compose.AttributeRow
import eu.petrfaruzel.nba.core.compose.AttributeRowData
import eu.petrfaruzel.nba.core.compose.NoContentScreen
import eu.petrfaruzel.nba.core.compose.TopBar
import eu.petrfaruzel.nba.domain.features.teams.models.TeamDO

@Composable
fun TeamDetailScreen(
    navController: NavHostController,
    teamDetail: TeamDO?
) {
    if (teamDetail != null) {
        TeamDetailScreenContent(
            navController = navController,
            team = teamDetail
        )
    } else {
        NoContentScreen()
    }
}


@Composable
private fun TeamDetailScreenContent(
    navController: NavHostController,
    team: TeamDO
) {

    val attributes = listOf(
        AttributeRowData(
            stringResource(id = R.string.team_full_name),
            team.fullName
        ) {},
        AttributeRowData(
            stringResource(id = R.string.team_abbrev),
            team.abbreviation
        ) {},
        AttributeRowData(
            stringResource(id = R.string.team_city),
            team.city
        ) {},
        AttributeRowData(
            stringResource(id = R.string.team_conference),
            team.conference
        ) {},
    )

    Column {
        TopBar(
            navController = navController,
            title = stringResource(id = R.string.team_detail),
            backNavigationEnabled = true
        )
        for (attrib in attributes) {
            AttributeRow(attrib)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TeamDetailScreenContentPreview() {
    TeamDetailScreenContent(
        team = TeamDO(),
        navController = rememberNavController()
    )
}