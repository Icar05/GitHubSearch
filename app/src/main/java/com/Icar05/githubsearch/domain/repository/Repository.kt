package com.Icar05.githubsearch.domain.repository

import com.Icar05.githubsearch.domain.model.SearchItem
import com.Icar05.githubsearch.domain.model.SearchResponce
import io.reactivex.Observable

interface Repository {
    
    fun searchRepos(name: String): Observable<SearchResponce>
    
    fun storeRepos(repos: List<SearchItem>): Observable<List<SearchItem>>
    
    fun getRepos(name: String): Observable<List<SearchItem>>
}