package com.sandymist.apidbsync

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FruitDao {
    @Query("SELECT * FROM fruits")
    fun getAll(): List<Fruit>

    @Insert
    fun insertAll(vararg fruits: Fruit)

    @Delete
    fun delete(fruit: Fruit)
}