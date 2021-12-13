package com.sandymist.apidbsync

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fruits")
data class Fruit(
    @PrimaryKey
    val name: String
)