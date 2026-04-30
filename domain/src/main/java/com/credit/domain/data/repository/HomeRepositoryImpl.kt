package com.credit.domain.data.repository

import com.credit.domain.datasource.HomeRemoteDataSource
import com.credit.domain.model.Post
import com.credit.domain.repository.HomeRepository
import com.credit.domain.util.ApiResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val remote: HomeRemoteDataSource
) : HomeRepository {
    override suspend fun getPosts(): ApiResult<List<Post>> = remote.fetchPosts()
}
