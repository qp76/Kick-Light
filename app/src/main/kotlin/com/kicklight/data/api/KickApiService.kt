package com.kicklight.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit API service for KICK.com API
 * Includes endpoints for streams, channels, categories, search, etc.
 */
interface KickApiService {

    // ==================== Live Streams ====================

    /**
     * Get live streams (paginated)
     * Public endpoint - no authentication required
     */
    @GET("api/v2/streams")
    suspend fun getLiveStreams(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20,
        @Query("sort") sort: String = "trending"
    ): LiveStreamsResponse

    /**
     * Get streams by category
     */
    @GET("api/v2/categories/{slug}/streams")
    suspend fun getStreamsByCategory(
        @Path("slug") categorySlug: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20
    ): LiveStreamsResponse

    // ==================== Channels ====================

    /**
     * Get channel information by slug
     */
    @GET("api/v2/channels/{slug}")
    suspend fun getChannel(
        @Path("slug") slug: String
    ): ChannelResponse

    /**
     * Search for channels
     */
    @GET("api/v2/channels/search")
    suspend fun searchChannels(
        @Query("query") query: String,
        @Query("limit") limit: Int = 20
    ): List<ChannelResponse>

    // ==================== Categories ====================

    /**
     * Get all categories
     */
    @GET("api/v2/categories")
    suspend fun getCategories(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 50
    ): List<CategoryResponse>

    /**
     * Get category by slug
     */
    @GET("api/v2/categories/{slug}")
    suspend fun getCategory(
        @Path("slug") slug: String
    ): CategoryResponse

    // ==================== Videos/VODs ====================

    /**
     * Get channel videos/VODs
     */
    @GET("api/v2/channels/{slug}/videos")
    suspend fun getChannelVideos(
        @Path("slug") slug: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20
    ): VideosResponse

    /**
     * Get video by slug
     */
    @GET("api/v2/videos/{slug}")
    suspend fun getVideo(
        @Path("slug") slug: String
    ): VideoResponse

    // ==================== Clips ====================

    /**
     * Get channel clips
     */
    @GET("api/v2/channels/{slug}/clips")
    suspend fun getChannelClips(
        @Path("slug") slug: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20
    ): ClipsResponse

    /**
     * Get clip by slug
     */
    @GET("api/v2/clips/{slug}")
    suspend fun getClip(
        @Path("slug") slug: String
    ): ClipResponse

    // ==================== Search ====================

    /**
     * Global search across channels, streams, and categories
     */
    @GET("api/v2/search")
    suspend fun search(
        @Query("query") query: String,
        @Query("type") type: String? = null,
        @Query("limit") limit: Int = 50
    ): SearchResponse
}
