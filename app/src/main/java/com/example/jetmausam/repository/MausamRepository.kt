package com.example.jetmausam.repository

import android.util.Log
import com.example.jetmausam.data.DataOrException
import com.example.jetmausam.model.geocoding.GeoCodingData
import com.example.jetmausam.model.mausam.MausamData
import com.example.jetmausam.network.MausamApi
import com.example.jetmausam.utils.AppConstants
import javax.inject.Inject

class MausamRepository @Inject constructor(private val api: MausamApi){

    private val geoCodingData = DataOrException<GeoCodingData, Boolean, Exception>()
    private val mausamData = DataOrException<MausamData, Boolean, Exception>()

    suspend fun getMausamData(
        latQuery: String,
        lonQuery: String,
    ): DataOrException <MausamData, Boolean, Exception> {

        try {
            mausamData.loading = true
            mausamData.data = api.getMausamData(lat = latQuery, lon = lonQuery)
            if (mausamData.data != null) {
                mausamData.loading = false
            }
        } catch(exception: Exception) {
            mausamData.e = exception
            Log.d("Aneesh Exception", "getMausamException: ${mausamData.e!!.localizedMessage}")
        }

        return mausamData

//        val response = try {
//            api.getMausamData(lat = latQuery, lon = lonQuery)
//        } catch(e: Exception) {
//            return DataOrException(e = e)
//        }
//        return DataOrException(response)
    }

    suspend fun getGeocodingData(
        city: String
    ): DataOrException<GeoCodingData, Boolean, Exception> {

        try {
            geoCodingData.loading = true
            geoCodingData.data = api.getCoordinates(q = "Delhi, IN")
            if(geoCodingData.data != null && geoCodingData.data!!.isNotEmpty()) {
                geoCodingData.loading = false
            }
        } catch(exception: Exception) {
            geoCodingData.e = exception
            Log.d("Aneesh Exception", "getGeocodingData: ${geoCodingData.e!!.localizedMessage}")
        }

        return geoCodingData

//        val response = try {
//            api.getCoordinates(q = city, limit = 1, appid = AppConstants.API_KEY)
//        } catch (e: Exception) {
//            return DataOrException(e = e)
//        }
//        return DataOrException(data = response)
    }

}