package com.kicklight.data.repository

import com.kicklight.data.api.KickApiService
import com.kicklight.data.api.LiveStreamsResponse
import com.kicklight.data.api.StreamResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for stream-related operations
 * Handles data fetching from API and caching
 */
@Singleton
class StreamRepository @Inject constructor(
    private val apiService: KickApiService
) {

    fun getLiveStreams(
        page: Int = 1,
        perPage: Int = 20,
        sort: String = "trending"
    ): Flow<Result<LiveStreamsResponse>> = flow {
        try {
            val result = apiService.getLiveStreams(page, perPage, sort)
            emit(Result.success(result))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    fun getStreamsByCategory(
        categorySlug: String,
        page: Int = 1,
        perPage: Int = 20
    ): Flow<Result<LiveStreamsResponse>> = flow {
        try {
            val result = apiService.getStreamsByCategory(categorySlug, page, perPage)
            emit(Result.success(result))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    fun getStream(streamId: String): Flow<Result<StreamResponse>> = flow {
        try {
            // Note: Fetch from live streams list as single stream endpoint may not exist
            val streams = apiService.getLiveStreams()
            val stream = streams.data.find { it.id == streamId || it.slug == streamId }
            if (stream != null) {
                emit(Result.success(stream))
            } else {
                emit(Result.failure(Exception("Stream not found")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
