package eu.petrfaruzel.nba.navigation

/**
 *  Simple navigation component
 *  Each screen is required to have it's own destination component
 *
 *  There are some existing screen examples
 *  @see PlayersScreenDestination
 *  @see PlayerDetailScreenDestination
 *  @see TeamDetailScreenDestination
 */
interface Destination {
    val route: String
}