package com.Icar05.githubsearch.presentation.app.di.module

import android.app.Application
import android.content.Context

import androidx.room.Room
import com.Icar05.githubsearch.data.dataProviders.LocalDataProviderImpl
import com.Icar05.githubsearch.data.repository.RepositoryImpl
import com.Icar05.githubsearch.data.storage.Database
import com.Icar05.githubsearch.domain.dataProviders.GlobalDataProvider
import com.Icar05.githubsearch.domain.dataProviders.LocalDataProvider
import com.Icar05.githubsearch.domain.repository.Repository
import com.Icar05.githubsearch.presentation.util.DialogUtil
import com.Icar05.githubsearch.presentation.util.ErrorHandler
import com.example.alex.githubsearchexample.app.BASE_URL
import com.example.alex.githubsearchexample.app.DATABASE_NAME
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class AppModule {
    
    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application.applicationContext
    }
    
    @Provides
    @Singleton
    @Named(BASE_URL)
    internal fun getBaseUrl(): String {
        return BASE_URL
    }
    
    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
        return httpClient.build()
    }
    
    @Provides
    @Singleton
    internal fun provideCallAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }
    
    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder().create()
    }
    
    @Provides
    @Singleton
    internal fun provideConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }
    
    @Provides
    @Singleton
    internal fun provideRetrofit(
        @Named(BASE_URL) baseUrl: String,
        okHttpClient: OkHttpClient,
        callAdapterFactory: CallAdapter.Factory,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converterFactory)
            .build()
    }
    
    @Provides
    @Singleton
    internal fun provideDatabase(context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build()
    }
    
    @Provides
    @Singleton
    internal fun provideGlobalDataProvider(retrofit: Retrofit): GlobalDataProvider {
        return retrofit.create(GlobalDataProvider::class.java)
    }
    
    @Provides
    @Singleton
    internal fun provideLocalDataProvider(database: Database): LocalDataProvider {
        return LocalDataProviderImpl(database)
    }
    
    @Provides
    @Singleton
    internal fun provideRepository(
        localDataProvider: LocalDataProvider,
        globalDataProvider: GlobalDataProvider
    ): Repository {
        return RepositoryImpl(
            localDataProvider,
            globalDataProvider
        )
    }
    
    @Provides
    fun provideDialogUtil() = DialogUtil()
    
    @Provides
    fun provideErrorHandler() = ErrorHandler()
    
}