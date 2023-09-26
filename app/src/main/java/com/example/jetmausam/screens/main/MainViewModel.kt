package com.example.jetmausam.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetmausam.data.DataOrException
import com.example.jetmausam.model.geocoding.GeoCodingData
import com.example.jetmausam.model.mausam.MausamDailyData
import com.example.jetmausam.model.mausam.MausamData
import com.example.jetmausam.repository.MausamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mausamRepository: MausamRepository): ViewModel() {

    private var _coordinatesData: MutableState<DataOrException<GeoCodingData, Boolean, Exception>>
    = mutableStateOf(DataOrException(null, true, Exception()))
    private var _mausamData: MutableState<DataOrException<MausamData, Boolean, Exception>>
    = mutableStateOf(DataOrException(null, true, Exception()))
    var mausamData: State<DataOrException<MausamData, Boolean, Exception>>
    = _mausamData

//    private var _mausamState: MutableState<DataOrException<MausamData, Boolean, Exception>>
//    = mutableStateOf(DataOrException(loading = true))
//    val mausamState: State<DataOrException<MausamData, Boolean, Exception>> = _mausamState
//
////    private var _dailyMausamListState: MutableState<List<MausamDailyData>>
////    = mutableStateOf(emptyList())
////    val dailyMausamListState: State<List<MausamDailyData>> = _dailyMausamListState
//
//    suspend fun getCoordinates(city: String): DataOrException<GeoCodingData, Boolean, Exception>
//    = mausamRepository.getGeocodingData(city = city)
//
//    suspend fun getMausam(lat: String?, lon: String?) : DataOrException<MausamData, Boolean, Exception> {
//        _mausamState.value = mausamRepository.getMausamData(latQuery = lat, lonQuery = lon)
//        Log.d("Aneesh", "getMausam: ${_mausamState.value.data!!.list}")
////        _dailyMausamListState.value = _mausamState.value.data!!.list
////        Log.d("Aneesh", "getMausam2: ${_dailyMausamListState.value}")
//        return mausamState.value
//    }

    fun getMausam(city: String) {
        viewModelScope.launch {
            _coordinatesData.value.loading = true
            _mausamData.value.loading = true
            _coordinatesData.value = mausamRepository.getGeocodingData(city)
            if(_coordinatesData.value.data.isNullOrEmpty()) {
                Log.d("Coordinates", "getMausam: Coordinates Error")
            } else {
                _coordinatesData.value.loading = false
                Log.d("Coordinates", "getMausam: ${_coordinatesData.value.data!![0].lat}")
                _mausamData.value = mausamRepository.getMausamData(
                    _coordinatesData.value.data!![0].lat.toString(),
                    _coordinatesData.value.data!![0].lon.toString()
                )
                Log.d("Coordinates", "getMausam: ${_mausamData.value.data!!.city}")
                if(_mausamData.value.data != null) {
                    _mausamData.value.loading = false
                }
            }
        }
    }

}