package com.nimesh.vasani.melodybeatblastkmp.player

interface MediaPlayerListener {
    fun onReady()
    fun onVideoCompleted()
    fun onError()
}
