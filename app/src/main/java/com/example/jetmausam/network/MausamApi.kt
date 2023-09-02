package com.example.jetmausam.network

import com.example.jetmausam.model.geocoding.GeoCodingData
import com.example.jetmausam.model.mausam.MausamData
import com.example.jetmausam.utils.AppConstants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface MausamApi {

    @GET("data/2.5/forecast")
    suspend fun getMausamData(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String = AppConstants.API_KEY
    ): MausamData

    @GET("geo/1.0/direct")
    suspend fun getCoordinates(
        @Query("q") q: String,
        @Query("limit") limit: Int = 1,
        @Query("appid") appid: String = AppConstants.API_KEY
    ): GeoCodingData

}