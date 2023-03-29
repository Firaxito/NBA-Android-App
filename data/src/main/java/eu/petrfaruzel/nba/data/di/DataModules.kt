package eu.petrfaruzel.nba.data.di

import eu.petrfaruzel.nba.data.api.RetrofitHelper
import eu.petrfaruzel.nba.data.api.services.players.apis.PlayerApi
import org.koin.dsl.module

val dataModules = module {
    single { RetrofitHelper.provideRetrofit() }

    single<PlayerApi> { RetrofitHelper.provideApi(get()) }
}