package com.nimesh.vasani.melodybeatblastkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform