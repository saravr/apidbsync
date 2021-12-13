package com.sandymist.apidbsync

import javax.inject.Inject

class FruitRepository @Inject constructor(
    private val webservice: Webservice,
    private val fruitDao: FruitDao
) {
    suspend fun getFruits(): List<Fruit> {
        return webservice.getFruits()
    }
}