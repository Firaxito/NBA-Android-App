package eu.petrfaruzel.nba.di

import eu.petrfaruzel.nba.domain.di.dataModules
import eu.petrfaruzel.nba.domain.di.domainModules
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    appDeclaration: KoinAppDeclaration
): KoinApplication = startKoin {
    appDeclaration()
    modules(
        modules = listOf(
            presentationModules,
            domainModules,
            dataModules
        )
    )
}
