package com.sandymist.apidbsync

import retrofit2.http.GET

interface Webservice {
    @GET("/")
    suspend fun getColors(): List<String>
}