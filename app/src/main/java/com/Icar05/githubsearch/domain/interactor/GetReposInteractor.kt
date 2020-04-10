package com.Icar05.githubsearch.domain.interactor

import com.Icar05.githubsearch.domain.model.SearchItem
import com.Icar05.githubsearch.domain.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject

open class GetReposInteractor
@Inject constructor(private val repository: Repository) {
    fun execute(name:String): Observable<List<SearchItem>> = repository.getRepos(name)
}
