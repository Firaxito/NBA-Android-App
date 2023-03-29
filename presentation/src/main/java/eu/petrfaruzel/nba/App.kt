package eu.petrfaruzel.nba

import android.app.Application
import eu.petrfaruzel.nba.di.initKoin
import org.koin.android.ext.koin.androidContext
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeKoin()

        // Debugging
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(Timber.DebugTree()) // TODO replace DebugTree with proper CrashReporting class
        }
    }


    private fun initializeKoin() {
        initKoin {
            androidContext(this@App)
        }
    }
}