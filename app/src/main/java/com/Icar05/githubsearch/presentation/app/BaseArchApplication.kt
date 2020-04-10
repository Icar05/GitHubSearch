package com.Icar05.githubsearch.presentation.app

import android.app.Application
import com.Icar05.githubsearch.presentation.di.component.DaggerAppComponent

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class BaseArchApplication : Application() , HasAndroidInjector {
    
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    
    override fun onCreate() {
        super.onCreate()
        
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
    
    
    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}