package eu.petrfaruzel.nba

import androidx.compose.runtime.mutableStateOf
import eu.petrfaruzel.nba.core.compose.logic.UIState
import eu.petrfaruzel.nba.domain.features.players.IPlayerRepository
import eu.petrfaruzel.nba.domain.features.players.models.MetaDataDO
import eu.petrfaruzel.nba.domain.features.players.models.PlayerDO
import eu.petrfaruzel.nba.domain.features.players.models.PlayerResultDO
import eu.petrfaruzel.nba.features.players.PlayersViewModel
import eu.petrfaruzel.nba.shared.ResultState
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 *  Class for Unit Testing PlayersViewModel
 *  @see PlayersViewModel
 */
class PlayersViewModelUnitTest {

    companion object {
        const val DEFAULT_DELAY = 10L
    }

    /**
     *  Mocked repository for ViewModel.
     *
     *  Always returning only 1 player per fetch and
     *  metadata with max of 2 pages with player's lastName of ${"currentPage"}
     */
    class MockPlayerRepository : IPlayerRepository {

        private val currentPage = mutableStateOf(0L)

        override suspend fun getPlayers(page: Int, count: Int): ResultState<PlayerResultDO> {
            delay(DEFAULT_DELAY)
            currentPage.value += 1
            return ResultState.Success(
                PlayerResultDO(
                    players = listOf(
                        PlayerDO(
                            firstName = "Player",
                            lastName = "${currentPage.value}"
                        )
                    ),
                    meta = MetaDataDO(
                        totalPages = 2L,
                        currentPage = currentPage.value,
                        nextPage = currentPage.value + 1,
                        perPage = 1,
                        totalCount = 2L
                    )
                )
            )
        }
    }

    /**
     * Test for initial state 'LoadingUIState'
     */
    @Test
    fun playersViewModelLoadingStateTest() {

        val playersViewModel = PlayersViewModel(
            MockPlayerRepository()
        )

        // Loading screen state
        val values = playersViewModel.playersViewState.value
        assert(values is UIState.LoadingUIState)

    }


    /**
     * Testing multiple sequential players loading
     * with default load within viewModel's Init{}
     *
     * This test checks loading limitation (of 2) also with multiple
     * accidental double fetches after short interval
     */
    @Test
    fun playersSequentialLoadingTest() {

        val playersViewModel = PlayersViewModel(
            MockPlayerRepository()
        )

        // Wait until data are loaded
        Thread.sleep(5 * DEFAULT_DELAY)

        // Screen is loaded
        val values1 = playersViewModel.playersViewState.value
        assert(values1 is UIState.LoadedUIState)

        values1 as UIState.LoadedUIState
        assertEquals(
            1,
            values1.value.size
        )
        assertEquals(
            "1",
            values1.value[0].lastName
        )

        // Try accidental double fetch
        runBlocking {
            playersViewModel.loadMorePlayers()
            delay(2)
            playersViewModel.loadMorePlayers()
            delay(3 * DEFAULT_DELAY)
        }

        val values2 = playersViewModel.playersViewState.value as UIState.LoadedUIState

        assertEquals(
            2,
            values2.value.size
        )
        assertEquals(
            "1",
            values2.value[0].lastName
        )
        assertEquals(
            "2",
            values2.value[1].lastName
        )

        // Try fetch after data are loaded fully loaded
        runBlocking {
            playersViewModel.loadMorePlayers()
            delay(3 * DEFAULT_DELAY)
            playersViewModel.loadMorePlayers()
            delay(3 * DEFAULT_DELAY)
        }

        val values3 = playersViewModel.playersViewState.value as UIState.LoadedUIState

        assertEquals(
            2,
            values3.value.size
        )
        assertEquals(
            "1",
            values3.value[0].lastName
        )
        assertEquals(
            "2",
            values3.value[1].lastName
        )
    }
}