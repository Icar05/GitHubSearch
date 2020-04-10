package com.Icar05.githubsearch.presentation.app

import android.app.Application
import com.Icar05.githubsearch.presentation.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class BaseArchApplication : Application() {
    
    private val modules = listOf(
        viewModelModule,
        repositoryModule,
        dataProvidersModule,
        networkModule,
        useCaseModule
    )
    
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidContext(this@BaseArchApplication)
            modules(modules)
        }
    }
}