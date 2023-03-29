package eu.petrfaruzel.nba.core.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import eu.petrfaruzel.nba.core.bases.BaseViewModel
import eu.petrfaruzel.nba.core.compose.logic.UIState

/**
 * Wrapper for Composable that works with ViewModel
 * and their loading state. It can display content based on
 * resulted UIState (Loading, Loaded, Error)
 * @see UIState
 * @see BaseViewModel
 */
@Composable
fun <T> ViewStateWrapper(
    viewState: UIState<T>,
    modifier: Modifier = Modifier,
    fillMaxSize: Boolean = true,
    loading: @Composable () -> Unit = {},
    error: @Composable () -> Unit = {},
    loaded: @Composable () -> Unit
) {
    if (fillMaxSize) modifier
        .fillMaxHeight()
        .fillMaxHeight()
    Box(modifier = modifier) {
        when (viewState) {
            is UIState.LoadingUIState -> loading()
            is UIState.LoadedUIState -> loaded()
            is UIState.ErrorUIState -> error()
        }
    }
}