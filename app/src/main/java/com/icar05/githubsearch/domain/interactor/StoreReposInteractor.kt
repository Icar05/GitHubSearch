package com.icar05.githubsearch.domain.interactor

import com.icar05.githubsearch.domain.model.SearchItem
import com.icar05.githubsearch.domain.repository.Repository


open class StoreReposInteractor constructor(private val repository: Repository) {
    suspend fun execute(repos: List<SearchItem>): List<SearchItem> = repository.storeRepos(repos = repos)
}