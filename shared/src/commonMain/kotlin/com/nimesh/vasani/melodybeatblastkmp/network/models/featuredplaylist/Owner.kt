package com.nimesh.vasani.melodybeatblastkmp.network.models.featuredplaylist


import com.nimesh.vasani.melodybeatblastkmp.network.models.featuredplaylist.ExternalUrls
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Owner(
    @SerialName("display_name")
    val displayName: String?,
    @SerialName("external_urls")
    val externalUrls: ExternalUrls?,
    @SerialName("href")
    val href: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("type")
    val type: String?,
    @SerialName("uri")
    val uri: String?
)