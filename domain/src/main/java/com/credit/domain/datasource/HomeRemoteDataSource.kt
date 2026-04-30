package com.credit.domain.datasource

import com.credit.domain.model.Post
import com.credit.domain.util.ApiResult

interface HomeRemoteDataSource {
    suspend fun fetchPosts(): ApiResult<List<Post>>
}
