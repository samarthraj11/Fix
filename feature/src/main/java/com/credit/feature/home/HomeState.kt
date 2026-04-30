package com.credit.feature.home

import com.credit.domain.model.Post

data class HomeState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val error: String? = null
)
