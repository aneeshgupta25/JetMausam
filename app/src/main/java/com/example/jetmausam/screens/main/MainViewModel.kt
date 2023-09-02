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
import com.example.jetmausam.model.mausam.MausamData
import com.example.jetmausam.repository.MausamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mausamRepository: MausamRepository): ViewModel() {

    private var _mausamState: MutableState<DataOrException<MausamData, Boolean, Exception>>
    = mutableStateOf(DataOrException(loading = true))
    val mausamState: State<DataOrException<MausamData, Boolean, Exception>> = _mausamState
    suspend fun getCoordinates(city: String): DataOrException<GeoCodingData, Boolean, Exception>
    = mausamRepository.getGeocodingData(city = city)

    suspend fun getMausam(lat: String?, lon: String?) : DataOrException<MausamData, Boolean, Exception> {
        _mausamState.value = mausamRepository.getMausamData(latQuery = lat, lonQuery = lon)
        return mausamState.value
    }

}