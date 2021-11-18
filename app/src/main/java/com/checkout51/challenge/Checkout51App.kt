package com.checkout51.challenge

import android.app.Application
import com.checkout51.challenge.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Checkout51App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@Checkout51App)
            modules(
                utilsModule,
                apiModule,
                networkModule,
                repositoryModule,
                utilsModule,
                viewModelModule
            )
        }
    }
}