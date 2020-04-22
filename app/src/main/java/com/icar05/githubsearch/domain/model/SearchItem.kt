package com.icar05.githubsearch.domain.model

data class SearchItem (
    val id: Int,
    val name: String,
    val full_name: String?,
    val description: String?,
    val url: String?,
    val language: String?,
    val forks: Int
)