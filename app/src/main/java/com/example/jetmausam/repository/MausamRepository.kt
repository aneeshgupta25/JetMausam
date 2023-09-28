package com.example.jetmausam.repository

import com.example.jetmausam.data.DataOrException
import com.example.jetmausam.model.geocoding.GeoCodingData
import com.example.jetmausam.model.current_day_mausam.CurrentDayMausamData
import com.example.jetmausam.model.seven_days_mausam.SevenDaysMausamData
import com.example.jetmausam.network.MausamApi
import com.example.jetmausam.utils.AppConstants
import javax.inject.Inject

class MausamRepository @Inject constructor(private val api: MausamApi){

    suspend fun getCurrentDayMausamData(
        city: String,
    ): DataOrException <CurrentDayMausamData, Boolean, Exception> {

        val response: CurrentDayMausamData = try {
            api.getCurrentDayData(q = city)
        } catch(e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(response)
    }

    suspend fun getSevenDaysMausamData(
        city: String
    ): DataOrException<SevenDaysMausamData, Boolean, Exception> {

        val response: SevenDaysMausamData = try {
            api.getSevenDaysData(q = city)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(response)
    }

    suspend fun getGeocodingData(
        city: String
    ): DataOrException<GeoCodingData, Boolean, Exception> {

        val response: GeoCodingData = try {
            api.getCoordinates(q = city, limit = 1, appid = AppConstants.API_KEY)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        if(response.isEmpty()) return DataOrException(e = Exception("City Not Found"))
        return DataOrException(data = response)
    }

}