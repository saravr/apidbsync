package com.sandymist.apidbsync

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class FruitRepository @Inject constructor(
    private val webservice: Webservice,
    private val fruitDao: FruitDao
) {
    suspend fun getFruits(): Flow<List<Fruit>> {
        var error = false
        return flow {
            val fruitsFromAPI = withContext(Dispatchers.IO) {
                try {
                    webservice.getFruits()
                } catch (exception: Exception) {
                    Log.e(TAG, "Failed to connect: ${exception.message}")
                    error = true
                    return@withContext listOf()
                }
            }
            emit(fruitsFromAPI)
            val fruitsFromDB = withContext(Dispatchers.IO) {
                if (!error) {
                    fruitDao.deleteAll()
                    fruitDao.insertAll(*fruitsFromAPI.toTypedArray())
                    fruitsFromAPI
                } else {
                    fruitDao.getAll()
                }
            }
            // TODO: emit only if different
            emit(fruitsFromDB)
        }
    }

    companion object {
        private const val TAG = "FruitRepository"
    }
}