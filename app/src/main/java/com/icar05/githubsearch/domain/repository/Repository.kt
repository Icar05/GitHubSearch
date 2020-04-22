package com.icar05.githubsearch.domain.repository

import com.icar05.githubsearch.data.model.Result
import com.icar05.githubsearch.domain.model.SearchItem
import com.icar05.githubsearch.domain.model.SearchResponse

interface Repository {
    
    suspend fun storeRepos(repos: List<SearchItem>): List<SearchItem>
    
    suspend fun getRepos(name: String): List<SearchItem>
    
    suspend fun searchRepos(name: String): Result<SearchResponse>
}