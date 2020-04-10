package com.Icar05.githubsearch.presentation.app.di.component

import android.app.Application
import com.Icar05.githubsearch.presentation.app.BaseArchApplication
import com.Icar05.githubsearch.presentation.app.di.module.AppModule
import com.Icar05.githubsearch.presentation.app.di.module.BuilderModule
import com.Icar05.githubsearch.presentation.app.di.module.ViewModelFactoryModule
import com.Icar05.githubsearch.presentation.app.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AppModule::class,
    AndroidInjectionModule::class,
    ViewModelFactoryModule::class,
    ViewModelModule::class,
    BuilderModule::class])
interface AppComponent {
    
    @Component.Builder
    interface Builder {
        
        @BindsInstance
        fun application(app: Application): Builder
        
        fun build(): AppComponent
    }
    
    fun inject(app: BaseArchApplication)
}