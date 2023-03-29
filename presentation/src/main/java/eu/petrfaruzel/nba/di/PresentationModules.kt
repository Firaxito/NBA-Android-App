package eu.petrfaruzel.nba.di

import eu.petrfaruzel.nba.features.players.PlayersViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val presentationModules = module {
    singleOf(::PlayersViewModel)
}