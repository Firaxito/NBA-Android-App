@file:OptIn(ExperimentalGlideComposeApi::class)

package eu.petrfaruzel.nba.features.players

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import eu.petrfaruzel.nba.R
import eu.petrfaruzel.nba.core.compose.OnBottomReached
import eu.petrfaruzel.nba.core.compose.ProgressBar
import eu.petrfaruzel.nba.core.compose.SimpleErrorScreen
import eu.petrfaruzel.nba.core.compose.ViewStateWrapper
import eu.petrfaruzel.nba.core.compose.logic.UIState
import eu.petrfaruzel.nba.domain.features.players.models.PlayerDO
import eu.petrfaruzel.nba.domain.features.players.models.TeamDO
import eu.petrfaruzel.nba.navigation.navigateToPlayerDetail
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

@Composable
fun PlayersScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: PlayersViewModel = koinViewModel()
) {
    val viewState = viewModel.playersViewState.collectAsState()

    // Coroutine scope have to be used here because of Koin's bug with viewModelScope
    val coroutineScope = rememberCoroutineScope()
    val canLoadMore = remember { mutableStateOf(true) }

    ViewStateWrapper(
        viewState = viewState.value,
        loading = { ProgressBar() },
        error = { SimpleErrorScreen() }) {

        val playersData = viewState.value as UIState.LoadedUIState

        PlayersScreenContent(
            players = playersData.value,
            navController = navController,
            canLoadMore = canLoadMore.value
        ) {
            Timber.d("Loading more players...")
            canLoadMore.value = viewModel.canLoadMore()
            coroutineScope.launch {
                if (canLoadMore.value) viewModel.loadMorePlayers()
            }
        }
    }
}

@Composable
private fun PlayersScreenContent(
    players: List<PlayerDO>,
    navController: NavHostController,
    canLoadMore: Boolean,
    preview: Boolean = false,
    onMoreLoadRequired: () -> Unit,
) {
    val listState = rememberLazyListState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        if (!preview) {
            GlideImage(
                model = "https://www.freepnglogos.com/uploads/nba-logo-png/nba-debate-club-milken-hottest-new-club-the-roar-25.png",
                contentDescription = "logo",
                modifier = Modifier.height(120.dp)
            )
        }

        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            text = stringResource(id = R.string.players_list)
        )

        Divider(thickness = 2.dp)

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxHeight()
        ) {
            items(players) { player ->
                PlayerItem(player = player) {
                    navController.navigateToPlayerDetail(player)
                }
                Divider(thickness = 1.dp)
                if (canLoadMore && players.lastOrNull() == player) {
                    ProgressBar(modifier = Modifier.height(80.dp))
                }
            }
        }

        listState.OnBottomReached {
            onMoreLoadRequired()
        }
    }
}

@Composable
private fun PlayerItem(
    player: PlayerDO,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(56.dp)
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${player.firstName} ${player.lastName}",
            modifier = Modifier.padding(end = 8.dp),
            fontWeight = FontWeight.Bold
        )
        Text(text = if (!player.position.isNullOrBlank()) "(${player.position})" else "")
        Spacer(modifier = Modifier.weight(1f))
        Text(text = player.team?.name ?: "")
    }
}

@Preview(showBackground = true)
@Composable
private fun TeamDetailScreenContentPreview() {
    PlayersScreenContent(
        players = listOf(
            PlayerDO(
                firstName = "Firstname",
                lastName = "1",
                position = "G",
                team = TeamDO(name = "Team name 1")
            ),
            PlayerDO(
                firstName = "Firstname",
                lastName = "2",
                position = "C",
                team = TeamDO(name = "Team name 2")
            ),
            PlayerDO(
                firstName = "Firstname",
                lastName = "3",
                team = TeamDO(name = "Team name 3")
            )
        ),
        navController = rememberNavController(),
        canLoadMore = true,
        preview = true
    ) {}
}