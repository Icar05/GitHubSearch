package com.icar05.githubsearch.domain.interactor

import com.icar05.githubsearch.data.model.Result
import com.icar05.githubsearch.domain.model.SearchResponse
import com.icar05.githubsearch.domain.repository.Repository

open class SearchReposInteractor constructor(private val repository: Repository) {
    suspend fun execute(name: String): Result<SearchResponse> = repository.searchRepos(name)
}
