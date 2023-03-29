package eu.petrfaruzel.nba.features.players

import androidx.lifecycle.viewModelScope
import eu.petrfaruzel.nba.core.bases.BaseViewModel
import eu.petrfaruzel.nba.core.compose.logic.UIState
import eu.petrfaruzel.nba.domain.features.players.IPlayerRepository
import eu.petrfaruzel.nba.domain.features.players.models.MetaDataDO
import eu.petrfaruzel.nba.domain.features.players.models.PlayerDO
import eu.petrfaruzel.nba.shared.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class PlayersViewModel(
    private val playerRepository: IPlayerRepository
) : BaseViewModel() {

    private val _playersViewState: MutableStateFlow<UIState<List<PlayerDO>>> =
        MutableStateFlow(UIState.LoadingUIState())
    val playersViewState: StateFlow<UIState<List<PlayerDO>>> = _playersViewState

    private var metadata: MetaDataDO? = null // Metadata of currently loaded items
    private var isLoading: Boolean = false // Parallel players loading prevention flag

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadMorePlayers()
        }
    }

    fun canLoadMore() = metadata == null || metadata?.currentPage != metadata?.totalPages

    suspend fun loadMorePlayers() {
        // If we reach end of pagination, return false and stop loading
        if (isLoading || !canLoadMore()) return

        isLoading = true

        val loadedData = playerRepository.getPlayers(
            page = metadata?.nextPage?.toInt() ?: 0,
            count = PAGING_SIZE_COUNT
        )

        when (loadedData) {
            is ResultState.Success -> {
                metadata = loadedData.value.meta
                Timber.d("Loaded more player: $metadata")

                val playersValues = playersViewState.value
                if (playersValues is UIState.LoadedUIState) { // Already loaded, adding more
                    _playersViewState.value =
                        UIState.LoadedUIState(playersValues.value.plus(loadedData.value.players))
                } else { // Not yet loaded
                    _playersViewState.value = UIState.LoadedUIState(loadedData.value.players)
                }
            }

            is ResultState.Error -> {
                _playersViewState.value = UIState.ErrorUIState(loadedData.errorMessage)
            }
        }

        isLoading = false
    }

    companion object {
        private const val PAGING_SIZE_COUNT = 35
    }

}