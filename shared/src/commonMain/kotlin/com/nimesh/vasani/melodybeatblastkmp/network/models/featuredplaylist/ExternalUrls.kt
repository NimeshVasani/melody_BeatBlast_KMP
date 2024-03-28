package com.nimesh.vasani.melodybeatblastkmp.network.models.featuredplaylist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalUrls(
    @SerialName("spotify")
    val spotify: String?
)