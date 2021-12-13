package com.sandymist.apidbsync

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class APIDBSyncHiltModule {

    @Provides
    @Singleton
    fun provideFruitDatabase(@ApplicationContext appContext: Context): FruitDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            FruitDatabase::class.java,
            "fruitsdb"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFruitDao(fruitDatabase: FruitDatabase): FruitDao {
        return fruitDatabase.fruitDao()
    }

    @Provides
    fun provideBaseUrl() = "http://skynote.sandymist.com:7911"

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient.Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideWebservice(retrofit: Retrofit): Webservice {
        return retrofit.create(Webservice::class.java)
    }
}