package com.kicklight.data.repository

import com.kicklight.data.api.ChannelResponse
import com.kicklight.data.api.KickApiService
import com.kicklight.data.api.VideosResponse
import com.kicklight.data.api.ClipsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for channel-related operations
 */
@Singleton
class ChannelRepository @Inject constructor(
    private val apiService: KickApiService
) {

    fun getChannel(slug: String): Flow<Result<ChannelResponse>> = flow {
        try {
            val result = apiService.getChannel(slug)
            emit(Result.success(result))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    fun searchChannels(query: String, limit: Int = 20): Flow<Result<List<ChannelResponse>>> = flow {
        try {
            val result = apiService.searchChannels(query, limit)
            emit(Result.success(result))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    fun getChannelVideos(
        slug: String,
        page: Int = 1,
        perPage: Int = 20
    ): Flow<Result<VideosResponse>> = flow {
        try {
            val result = apiService.getChannelVideos(slug, page, perPage)
            emit(Result.success(result))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    fun getChannelClips(
        slug: String,
        page: Int = 1,
        perPage: Int = 20
    ): Flow<Result<ClipsResponse>> = flow {
        try {
            val result = apiService.getChannelClips(slug, page, perPage)
            emit(Result.success(result))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
