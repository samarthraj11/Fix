package com.credit.domain.repository

import com.credit.domain.model.Post
import com.credit.domain.util.ApiResult

interface HomeRepository {
    suspend fun getPosts(): ApiResult<List<Post>>
}
