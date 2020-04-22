package com.icar05.githubsearch.domain.extension

import com.icar05.githubsearch.data.model.GitHabInfoEntity
import com.icar05.githubsearch.domain.model.SearchItem
import retrofit2.Response


fun GitHabInfoEntity.toSearchItem(): SearchItem = SearchItem(
    id = id,
    name = name,
    description = description,
    full_name = full_name,
    url = url,
    language = language,
    forks = forks
)

fun SearchItem.toGitHubInfoEntity(): GitHabInfoEntity = GitHabInfoEntity(
    id = id,
    name = name,
    description = description,
    full_name = full_name,
    url = url,
    language = language,
    forks = forks
)