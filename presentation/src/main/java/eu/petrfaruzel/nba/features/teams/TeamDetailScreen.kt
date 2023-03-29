package eu.petrfaruzel.nba.features.teams

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.petrfaruzel.nba.R
import eu.petrfaruzel.nba.core.compose.AttributeRow
import eu.petrfaruzel.nba.core.compose.AttributeRowData
import eu.petrfaruzel.nba.core.compose.NoContentScreen
import eu.petrfaruzel.nba.domain.features.players.models.TeamDO

@Composable
fun TeamDetailScreen(
    teamDetail: TeamDO?
) {
    if (teamDetail != null) {
        TeamDetailScreenContent(
            team = teamDetail
        )
    } else {
        NoContentScreen()
    }
}


@Composable
private fun TeamDetailScreenContent(
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.team_detail)
            )
        }
        Divider(modifier = Modifier.height(2.dp))
        for (attrib in attributes) {
            AttributeRow(attrib)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TeamDetailScreenContentPreview(){
    TeamDetailScreenContent(team = TeamDO())
}