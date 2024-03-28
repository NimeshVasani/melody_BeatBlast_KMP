package com.nimesh.vasani.melodybeatblastkmp.network

import com.nimesh.vasani.melodybeatblastkmp.TOKEN
import com.nimesh.vasani.melodybeatblastkmp.dummydata.featurePlaylistResponse
import com.nimesh.vasani.melodybeatblastkmp.dummydata.newReleases
import com.nimesh.vasani.melodybeatblastkmp.dummydata.topFiftyChartsResponse
import com.nimesh.vasani.melodybeatblastkmp.network.models.featuredplaylist.FeaturedPlayList
import com.nimesh.vasani.melodybeatblastkmp.network.models.newreleases.NewReleasedAlbums
import com.nimesh.vasani.melodybeatblastkmp.network.models.topfiftycharts.TopFiftyCharts
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.submitForm
import io.ktor.client.statement.bodyAsText
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.InternalAPI
import io.ktor.util.appendIfNameAbsent
import io.ktor.util.encodeBase64
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


class SpotifyApiImpl : SpotifyApi {
    override suspend fun getTopFiftyChart(): TopFiftyCharts {


        if (TOKEN.isEmpty()) {
            return Json.decodeFromString<TopFiftyCharts>(topFiftyChartsResponse)
        }
        return client.get {

            headers {
                sptifyEndPoint("v1/playlists/3cEYpjA9oz9GiPac4AsH4n")
            }

        }.body()
    }

    override suspend fun getNewReleases(): NewReleasedAlbums {
        if (TOKEN.isEmpty()) {
            return Json.decodeFromString(newReleases)
        }
        return client.get {
            headers {
                sptifyEndPoint("v1/browse/new-releases")
            }
        }.body()
    }

    override suspend fun getFeaturedPlaylist(): FeaturedPlayList {
        if (TOKEN.isEmpty()) {
            return Json.decodeFromString<FeaturedPlayList>(featurePlaylistResponse)
        }
        return client.get {
            headers {
                sptifyEndPoint("v1/browse/featured-playlists")
            }
        }.body()
    }

    override suspend fun getPlayList(playlistId: String): TopFiftyCharts {
        if (TOKEN.isEmpty()) {
            return Json.decodeFromString<TopFiftyCharts>(topFiftyChartsResponse)
        }
        return client.get {
            headers {
                sptifyEndPoint("v1/playlists/37i9dQZF1E362MRuTYGnA6")
            }
        }.body()
    }


    private val client = HttpClient {
        expectSuccess = true
        install(HttpTimeout) {
            val timeout = 30000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }
        install(ContentNegotiation) {
            json(Json { isLenient = true; ignoreUnknownKeys = true })
        }
    }


    private fun HttpRequestBuilder.sptifyEndPoint(path: String) {
        url {
            takeFrom("https://api.spotify.com/")
            encodedPath = path
            headers {
                append(
                    HttpHeaders.Authorization, TOKEN
                )
            }
        }
    }


}
