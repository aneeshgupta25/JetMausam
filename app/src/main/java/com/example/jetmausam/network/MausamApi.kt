package com.example.jetmausam.network

import com.example.jetmausam.model.seven_days_mausam.SevenDaysMausamData
import com.example.jetmausam.model.geocoding.GeoCodingData
import com.example.jetmausam.model.current_day_mausam.CurrentDayMausamData
import com.example.jetmausam.utils.AppConstants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface MausamApi {

    //get weather details
    @GET("data/2.5/forecast")
    suspend fun getCurrentDayData(
        @Query("q") q: String,
        @Query("cnt") cnt: Int = 8,
        @Query("units") units: String,
        @Query("appid") appid: String = AppConstants.API_KEY
    ): CurrentDayMausamData

    // get coordinates for city
    @GET("geo/1.0/direct")
    suspend fun getCoordinates(
        @Query("q") q: String,
        @Query("limit") limit: Int = 1,
        @Query("appid") appid: String = AppConstants.API_KEY
    ): GeoCodingData

    @GET("data/2.5/forecast/daily")
    suspend fun getSevenDaysData(
        @Query("q") q: String,
        @Query("cnt") cnt: Int = 7,
        @Query("units") units: String,
        @Query("appid") appid: String = AppConstants.API_KEY
    ): SevenDaysMausamData

}