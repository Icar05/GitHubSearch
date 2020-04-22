package com.icar05.githubsearch.presentation.app

import android.app.Application
import com.icar05.githubsearch.presentation.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class BaseArchApplication : Application() {
    
    private val modules = listOf(
        viewModelModule,
        repositoryModule,
        dataProvidersModule,
        networkModule,
        useCaseModule,
        utilModule
    )
    
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidContext(this@BaseArchApplication)
            modules(modules)
        }
    }
}