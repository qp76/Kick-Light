package com.kicklight.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// ==================== Stream Models ====================

@Serializable
data class StreamResponse(
    @SerialName("id")
    val id: String,
    @SerialName("slug")
    val slug: String,
    @SerialName("title")
    val title: String,
    @SerialName("viewers")
    val viewers: Int,
    @SerialName("thumbnail")
    val thumbnail: String? = null,
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("channel")
    val channel: ChannelResponse? = null,
    @SerialName("category")
    val category: CategoryResponse? = null,
    @SerialName("is_live")
    val isLive: Boolean = false
)

@Serializable
data class LiveStreamsResponse(
    @SerialName("data")
    val data: List<StreamResponse> = emptyList(),
    @SerialName("pagination")
    val pagination: PaginationResponse? = null
)

// ==================== Channel Models ====================

@Serializable
data class ChannelResponse(
    @SerialName("id")
    val id: String,
    @SerialName("user_id")
    val userId: String? = null,
    @SerialName("slug")
    val slug: String,
    @SerialName("username")
    val username: String,
    @SerialName("email")
    val email: String? = null,
    @SerialName("profile_pic")
    val profilePic: String? = null,
    @SerialName("banner")
    val banner: String? = null,
    @SerialName("bio")
    val bio: String? = null,
    @SerialName("verified")
    val verified: Boolean = false,
    @SerialName("followers_count")
    val followersCount: Int = 0,
    @SerialName("description")
    val description: String? = null,
    @SerialName("avatar_url")
    val avatarUrl: String? = null
)

// ==================== Category Models ====================

@Serializable
data class CategoryResponse(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("slug")
    val slug: String,
    @SerialName("icon")
    val icon: String? = null
)

// ==================== VOD / Video Models ====================

@Serializable
data class VideoResponse(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("slug")
    val slug: String,
    @SerialName("thumbnail")
    val thumbnail: String? = null,
    @SerialName("duration")
    val duration: Int? = null,
    @SerialName("views")
    val views: Int = 0,
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("channel")
    val channel: ChannelResponse? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class VideosResponse(
    @SerialName("data")
    val data: List<VideoResponse> = emptyList(),
    @SerialName("pagination")
    val pagination: PaginationResponse? = null
)

// ==================== Clip Models ====================

@Serializable
data class ClipResponse(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("slug")
    val slug: String,
    @SerialName("thumbnail")
    val thumbnail: String? = null,
    @SerialName("duration")
    val duration: Int? = null,
    @SerialName("views")
    val views: Int = 0,
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("channel")
    val channel: ChannelResponse? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class ClipsResponse(
    @SerialName("data")
    val data: List<ClipResponse> = emptyList(),
    @SerialName("pagination")
    val pagination: PaginationResponse? = null
)

// ==================== Search Models ====================

@Serializable
data class SearchResponse(
    @SerialName("channels")
    val channels: List<ChannelResponse> = emptyList(),
    @SerialName("streams")
    val streams: List<StreamResponse> = emptyList(),
    @SerialName("categories")
    val categories: List<CategoryResponse> = emptyList()
)

// ==================== Pagination ====================

@Serializable
data class PaginationResponse(
    @SerialName("page")
    val page: Int = 1,
    @SerialName("per_page")
    val perPage: Int = 20,
    @SerialName("total")
    val total: Int = 0
)

// ==================== Error Models ====================

@Serializable
data class ErrorResponse(
    @SerialName("message")
    val message: String,
    @SerialName("errors")
    val errors: Map<String, List<String>>? = null
)
