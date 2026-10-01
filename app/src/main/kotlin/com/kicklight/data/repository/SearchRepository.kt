package com.kicklight.data.repository

import com.kicklight.data.api.KickApiService
import com.kicklight.data.api.SearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for search operations
 */
@Singleton
class SearchRepository @Inject constructor(
    private val apiService: KickApiService
) {

    fun search(
        query: String,
        type: String? = null,
        limit: Int = 50
    ): Flow<Result<SearchResponse>> = flow {
        try {
            val result = apiService.search(query, type, limit)
            emit(Result.success(result))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
