package com.icar05.githubsearch.domain.model


data class SearchResponse (
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<SearchItem>
)