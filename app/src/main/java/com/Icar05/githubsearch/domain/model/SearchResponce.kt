package com.Icar05.githubsearch.domain.model

data class SearchResponce (
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<SearchItem>
)