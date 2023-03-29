package eu.petrfaruzel.nba.domain.di

import eu.petrfaruzel.nba.domain.features.players.IPlayerRepository
import eu.petrfaruzel.nba.domain.features.players.PlayerRepository
import org.koin.dsl.module

// Required for presentation module reference
val dataModules = eu.petrfaruzel.nba.data.di.dataModules

val domainModules  = module {
    single<IPlayerRepository> { PlayerRepository(get()) }
}