package com.icar05.githubsearch.domain.dataProviders

import com.icar05.githubsearch.domain.model.SearchItem

interface LocalDataProvider {
    
    suspend fun storeRepos(repos: List<SearchItem>): List<SearchItem>
    
    suspend fun getRepos(name: String): List<SearchItem>
    
}