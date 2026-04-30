package com.credit.domain.data.dto

import com.credit.domain.model.Post
import kotlinx.serialization.Serializable

@Serializable
data class PostDto(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
) {
    fun toDomain(): Post = Post(id = id, userId = userId, title = title, body = body)
}
