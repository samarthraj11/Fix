package com.credit.domain.data.remote

import com.credit.domain.data.dto.PostDto
import com.credit.domain.datasource.HomeRemoteDataSource
import com.credit.domain.model.Post
import com.credit.domain.util.ApiResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRemoteDataSourceImpl @Inject constructor(
    private val client: HttpClient
) : HomeRemoteDataSource {
    override suspend fun fetchPosts(): ApiResult<List<Post>> = try {
        val dtos: List<PostDto> = client.get("https://jsonplaceholder.typicode.com/posts").body()
        ApiResult.Success(dtos.map { it.toDomain() })
    } catch (e: Exception) {
        ApiResult.Error(message = e.message ?: "Something went wrong", cause = e)
    }
}
