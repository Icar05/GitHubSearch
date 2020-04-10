package com.Icar05.githubsearch.data.repository

import com.Icar05.githubsearch.domain.dataProviders.GlobalDataProvider
import com.Icar05.githubsearch.domain.dataProviders.LocalDataProvider
import com.Icar05.githubsearch.domain.model.SearchItem
import com.Icar05.githubsearch.domain.model.SearchResponce
import com.Icar05.githubsearch.domain.repository.Repository
import io.reactivex.Observable


class RepositoryImpl(
    private val localDataProvider: LocalDataProvider,
    private val globalDataProvider: GlobalDataProvider
) : Repository {
    
    override fun searchRepos(name: String): Observable<SearchResponce> = globalDataProvider.searchRepos(name = name)
    
    override fun storeRepos(repos: List<SearchItem>): Observable<List<SearchItem>> = localDataProvider.storeRepos(repos = repos)
    
    override fun getRepos(name: String): Observable<List<SearchItem>> = localDataProvider.getRepos(name = name)
}