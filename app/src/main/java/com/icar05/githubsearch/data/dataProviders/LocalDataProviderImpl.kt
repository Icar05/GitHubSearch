package com.icar05.githubsearch.data.dataProviders


import com.icar05.githubsearch.data.storage.Database
import com.icar05.githubsearch.domain.dataProviders.LocalDataProvider
import com.icar05.githubsearch.domain.extension.toGitHubInfoEntity
import com.icar05.githubsearch.domain.extension.toSearchItem
import com.icar05.githubsearch.domain.model.SearchItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class LocalDataProviderImpl (
    private val database: Database
) : LocalDataProvider {
    
    
    override suspend fun storeRepos(repos: List<SearchItem>): List<SearchItem> {
        withContext(Dispatchers.Default) {
            database.itemDao().insertAll(items = repos.map { it.toGitHubInfoEntity() })
        }
        return  repos
    }
    
    override suspend fun getRepos(name: String): List<SearchItem> {
        return withContext(Dispatchers.Default) {
            database.itemDao().getRepos(name = name).map { it.toSearchItem() }
        }
    }

}