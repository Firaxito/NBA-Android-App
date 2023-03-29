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
import eu.petrfaruzel.nba.navigation.navigateToPlayerDetail
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

@Composable
fun PlayersScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: PlayersViewModel = koinViewModel()
) {
    // Coroutine scope have to be used here because of Koin's bug with viewModelScope
    val coroutineScope = rememberCoroutineScope()

    val viewState = viewModel.playersViewState.collectAsState()
    val canLoadMore = remember { mutableStateOf(true) }
    val listState = rememberLazyListState()

    ViewStateWrapper(
        viewState = viewState.value,
        loading = { ProgressBar() },
        error = { SimpleErrorScreen() }) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val playerData = viewState.value as UIState.LoadedUIState

            GlideImage(
                model = "https://www.freepnglogos.com/uploads/nba-logo-png/nba-debate-club-milken-hottest-new-club-the-roar-25.png",
                contentDescription = "logo",
                modifier = Modifier.height(120.dp)
            )

            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.players_list)
            )

            Divider(thickness = 2.dp)

            LazyColumn(state = listState, modifier = Modifier.fillMaxHeight()) {
                items(playerData.value) { player ->
                    PlayerItem(player = player) {
                        navController.navigateToPlayerDetail(player)
                    }
                    Divider(thickness = 1.dp)
                    if(canLoadMore.value && playerData.value.lastOrNull() == player){
                        ProgressBar(modifier = Modifier.height(80.dp))
                    }
                }
            }

            listState.OnBottomReached {
                Timber.d("Loading more players")
                canLoadMore.value = viewModel.canLoadMore()
                coroutineScope.launch {
                    if (canLoadMore.value) viewModel.loadMorePlayers()
                }
            }
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
        Text(text = "${player.firstName} ${player.lastName}")
        Spacer(modifier = Modifier.weight(1f))
        Text(text = player.team?.name ?: "")
    }
}