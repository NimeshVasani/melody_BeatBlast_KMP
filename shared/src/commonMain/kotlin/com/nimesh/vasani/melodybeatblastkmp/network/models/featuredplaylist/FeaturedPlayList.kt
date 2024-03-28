package com.nimesh.vasani.melodybeatblastkmp.network.models.featuredplaylist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeaturedPlayList(
    @SerialName("message")
    val message: String?,
    @SerialName("playlists")
    val playlists: Playlists?
)