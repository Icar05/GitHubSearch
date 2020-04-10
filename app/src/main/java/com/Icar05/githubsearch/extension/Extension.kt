package com.Icar05.githubsearch.extension

import com.Icar05.githubsearch.data.model.GitHabInfoEntity
import com.Icar05.githubsearch.domain.model.SearchItem

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