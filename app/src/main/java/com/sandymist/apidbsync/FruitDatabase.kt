package com.sandymist.apidbsync

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Fruit::class], version = 1)
abstract class FruitDatabase: RoomDatabase() {

    abstract fun fruitDao(): FruitDao

    companion object {
        private lateinit var INSTANCE: FruitDatabase

        fun getDatabase(context: Context): FruitDatabase {
            synchronized(FruitDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FruitDatabase::class.java,
                        "fruitsdb"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}