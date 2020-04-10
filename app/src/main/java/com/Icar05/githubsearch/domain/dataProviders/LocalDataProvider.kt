package com.Icar05.githubsearch.domain.dataProviders

import com.Icar05.githubsearch.domain.model.SearchItem
import io.reactivex.Observable

interface LocalDataProvider {
    
    fun storeRepos(repos: List<SearchItem>): Observable<List<SearchItem>>
    
    fun getRepos(name: String): Observable<List<SearchItem>>
    
}