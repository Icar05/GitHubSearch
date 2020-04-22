package com.icar05.githubsearch.domain.interactor

import com.icar05.githubsearch.domain.model.SearchItem
import com.icar05.githubsearch.domain.repository.Repository

open class GetReposInteractor constructor(private val repository: Repository) {
    suspend fun execute(name:String): List<SearchItem> {
        return repository.getRepos(name = name)
    }
}
