package eu.petrfaruzel.nba.core.compose.logic

sealed class UIState<T>{
    data class LoadedUIState<T>(val value: T): UIState<T>()
    data class ErrorUIState<T>(val errorMessage: String? = null): UIState<T>()
    data class LoadingUIState<T>(val loadingMessage: String? = null): UIState<T>()
}

