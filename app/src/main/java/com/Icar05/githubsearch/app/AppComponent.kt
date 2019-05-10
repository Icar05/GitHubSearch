package com.Icar05.githubsearch.app

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class
    , AndroidInjectionModule::class
    , ActivityBindingModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}