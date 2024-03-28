package com.nimesh.vasani.melodybeatblastkmp

import io.ktor.client.*
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readText
import io.ktor.http.*
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


/**
 * Created by abdulbasit on 26/02/2023.
 */

var TOKEN = "Bearer BQAsqYu6s8lFMxix83Z0t5cWDjB6ZjCIHquxeOjNbC3TnbVXVb7m0TUZLeauMbeQTjtKHZIb68MUwvigN05ZTBReahTdsF9bQR-35pFVff6HPEBhQyqLEKn0DJ6iZvAN3sfo0vCHQjJi2F-c5f_6k7Ra8gK0h4VHczaRzzWPI5wBROt9tGbKu6pZdW-slBOU1O7jFKhPzvUVqCo54avCqmir4XMpNeK5AabU9Q3bjezAb54vUtToqaPaZs7-BPO7nEK82zT3vbFPIQ72RZ8vI4tu"

