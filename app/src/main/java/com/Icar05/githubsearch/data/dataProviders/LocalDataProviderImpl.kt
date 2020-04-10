package com.Icar05.githubsearch.data.dataProviders


import com.Icar05.githubsearch.data.storage.Database
import com.Icar05.githubsearch.domain.dataProviders.LocalDataProvider
import com.Icar05.githubsearch.domain.model.SearchItem
import com.Icar05.githubsearch.extension.toGitHubInfoEntity
import com.Icar05.githubsearch.extension.toSearchItem
import io.reactivex.Observable

class LocalDataProviderImpl (
    private val database: Database
) : LocalDataProvider {
    
    override fun storeRepos(repos: List<SearchItem>): Observable<List<SearchItem>> {
        return Observable.fromCallable {
            database.itemDao().insertAll(repos.map { it.toGitHubInfoEntity() })
            repos
        }
    }
    
    override fun getRepos(name: String): Observable<List<SearchItem>> {
        return Observable.fromCallable {
            database.itemDao().getRepos("%$name%").map { it.toSearchItem() }
        }
    }
}