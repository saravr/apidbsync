package com.sandymist.apidbsync

import javax.inject.Inject

class APIDBSyncRepository @Inject constructor(
    private val webservice: Webservice
) {
    suspend fun getColors() = webservice.getColors()
}