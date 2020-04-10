package com.Icar05.githubsearch.presentation.di


import androidx.room.Room
import com.Icar05.githubsearch.data.dataProviders.LocalDataProviderImpl
import com.Icar05.githubsearch.data.repository.RepositoryImpl
import com.Icar05.githubsearch.data.storage.Database
import com.Icar05.githubsearch.domain.dataProviders.GlobalDataProvider
import com.Icar05.githubsearch.domain.dataProviders.LocalDataProvider
import com.Icar05.githubsearch.domain.interactor.GetReposInteractor
import com.Icar05.githubsearch.domain.interactor.SearchReposInteractor
import com.Icar05.githubsearch.domain.interactor.StoreReposInteractor
import com.Icar05.githubsearch.domain.repository.Repository
import com.Icar05.githubsearch.presentation.util.DialogUtil
import com.Icar05.githubsearch.presentation.util.ErrorHandler
import com.Icar05.githubsearch.presentation.viewmodel.SearchViewModel
import com.example.alex.githubsearchexample.app.BASE_URL
import com.example.alex.githubsearchexample.app.DATABASE_NAME
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { SearchViewModel(get(),  get(), get()) }
}

val utilModule = module {
    single{DialogUtil() }
    single{ErrorHandler()}
}

val dataProvidersModule = module {
    single { Room.databaseBuilder(androidApplication(), Database::class.java, DATABASE_NAME).build() }
    single<LocalDataProvider> { LocalDataProviderImpl(get()) }
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addCallAdapterFactory(get())
            .addConverterFactory(get())
            .build()
            .create(GlobalDataProvider::class.java)
    }
}

val repositoryModule = module {
    single<Repository> { RepositoryImpl(get(), get()) }
}

val networkModule = module {
    single {
        val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
        return@single httpClient.build()
    }
    single<CallAdapter.Factory> { RxJava2CallAdapterFactory.create() }
    single { GsonBuilder().create() }
    single<Converter.Factory> { GsonConverterFactory.create(get()) }
}

val useCaseModule = module {
    single { GetReposInteractor(get()) }
    single { SearchReposInteractor(get()) }
    single { StoreReposInteractor(get()) }
}
