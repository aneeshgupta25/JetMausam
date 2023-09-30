package com.example.jetmausam.di

import android.content.Context
import androidx.room.Room
import com.example.jetmausam.data.MausamDao
import com.example.jetmausam.data.MausamDatabase
import com.example.jetmausam.network.MausamApi
import com.example.jetmausam.repository.MausamRepository
import com.example.jetmausam.utils.AppConstants
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
object AppModule {

    @Singleton
    @Provides
    fun provideMausamDao(mausamDatabase: MausamDatabase): MausamDao
        = mausamDatabase.mausamDao()

    @Singleton
    @Provides
    fun provideMausamDatabase(@ApplicationContext context: Context): MausamDatabase
    = Room.databaseBuilder(
        context,
        MausamDatabase::class.java,
        "mausam_db"
    ).build()


    @Singleton
    @Provides
    fun provideMausamApi(): MausamApi {

        val httpClient = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MausamApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMausamRepository(api: MausamApi): MausamRepository {
        return MausamRepository(api = api)
    }

}