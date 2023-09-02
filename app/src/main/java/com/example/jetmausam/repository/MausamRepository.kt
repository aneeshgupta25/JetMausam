package com.example.jetmausam.repository

import android.util.Log
import com.example.jetmausam.data.DataOrException
import com.example.jetmausam.model.geocoding.GeoCodingData
import com.example.jetmausam.model.mausam.MausamData
import com.example.jetmausam.network.MausamApi
import com.example.jetmausam.utils.AppConstants
import javax.inject.Inject

class MausamRepository @Inject constructor(private val api: MausamApi){

    suspend fun getMausamData(
        latQuery: String?,
        lonQuery: String?,
    ): DataOrException <MausamData, Boolean, Exception> {

        val response = try {
            api.getMausamData(lat = latQuery, lon = lonQuery)
        } catch(e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(response)
    }

    suspend fun getGeocodingData(
        city: String
    ): DataOrException<GeoCodingData, Boolean, Exception> {

        val response = try {
            api.getCoordinates(q = city, limit = 1, appid = AppConstants.API_KEY)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }

}