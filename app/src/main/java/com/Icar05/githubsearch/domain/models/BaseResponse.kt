package com.Icar05.githubsearch.domain.models

import com.Icar05.githubsearch.domain.models.Item

data class BaseResponse (
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<Item>
)