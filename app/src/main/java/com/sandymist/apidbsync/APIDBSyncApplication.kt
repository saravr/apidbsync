package com.sandymist.apidbsync

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class APIDBSyncApplication: Application() {
    private val database by lazy { FruitDatabase.getDatabase(this) }
}