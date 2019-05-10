package com.Icar05.githubsearch.data.dataProviders.local


import com.Icar05.githubsearch.data.database.Database
import com.Icar05.githubsearch.domain.models.Item
import io.reactivex.Observable

class LocalDataProviderImpl(private val database: Database) : LocalDataProvider {

    override fun storeRepos(repos: List<Item>): Observable<List<Item>> {
        return Observable.fromCallable {
            database.itemDao().insertAll(repos)
            repos
        }
    }

    override fun getRepos(name:String): Observable<List<Item>>{
        return Observable.fromCallable {
            database.itemDao().getRepos("%$name%")
        }
    }

}