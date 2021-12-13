package com.sandymist.apidbsync

import javax.inject.Inject

class FruitRepository @Inject constructor(
    private val webservice: Webservice
) {
    suspend fun getFruits() = webservice.getFruits()
}