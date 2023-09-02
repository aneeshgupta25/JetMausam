package com.example.jetmausam.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetmausam.data.DataOrException
import com.example.jetmausam.model.geocoding.GeoCodingData
import com.example.jetmausam.model.mausam.MausamData
import com.example.jetmausam.repository.MausamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mausamRepository: MausamRepository): ViewModel() {

    val geocodingData: MutableState<DataOrException<GeoCodingData, Boolean, Exception>>
    = mutableStateOf(DataOrException(null, true, Exception("")))
    val mausamData: MutableState<DataOrException<MausamData, Boolean, Exception>>
    = mutableStateOf(DataOrException(null, true, Exception("")))

    init {
        loadGeoCoding("Delhi")
    }

    private fun loadGeoCoding(city: String) {
        viewModelScope.launch {
            geocodingData.value.loading = true
            geocodingData.value = mausamRepository.getGeocodingData(city)
            if(geocodingData.value.data != null && geocodingData.value.data!!.isNotEmpty()) {
                geocodingData.value.loading = false
                var lat = geocodingData.value.data!![0].lat.toString()
                var lon = geocodingData.value.data!![0].lon.toString()
                loadMausam(lat = lat, lon = lon)
            }
            Log.d("Aneesh", "loadGeoCoding: ${geocodingData.value.data}")
        }
    }

    private fun loadMausam(lat: String, lon: String) {
        viewModelScope.launch {
            if(lat.isEmpty() || lon.isEmpty()) return@launch
            mausamData.value.loading = true
            mausamData.value = mausamRepository.getMausamData(latQuery = lat, lonQuery = lon)
            if(mausamData.value.data != null) {
                mausamData.value.loading = false
            }
            Log.d("Aneesh", "loadMausam: ${mausamData.value.data}")
        }
    }

}