package eu.petrfaruzel.nba.shared

import java.lang.Exception

sealed class ResultState<T> {
    data class Success<T>(val value: T) : ResultState<T>()
    data class Error<T>(val errorMessage: String? = null, val e: Exception? = null) : ResultState<T>()
}