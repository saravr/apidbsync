package com.sandymist.apidbsync

import androidx.room.*

@Dao
interface FruitDao {
    @Query("SELECT * FROM fruits")
    fun getAll(): List<Fruit>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg fruits: Fruit)

    @Delete
    fun delete(fruit: Fruit)

    @Query("DELETE FROM fruits")
    fun deleteAll()
}