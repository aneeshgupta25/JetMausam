package com.example.jetmausam.screens.main

import android.util.Log
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.navigation.NavController
import com.example.jetmausam.data.DataOrException
import com.example.jetmausam.model.geocoding.GeoCodingData
import com.example.jetmausam.model.mausam.MausamData

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    val geocodingData = produceState<DataOrException<GeoCodingData, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = viewModel.getCoordinates("Delhi, IN")
    }
    val mausamData = viewModel.mausamState.value
    LaunchedEffect(key1 = geocodingData.value) {
        if(geocodingData.value.loading != true) {
            val longitude = geocodingData.value.data!![0].lon
            val latitude = geocodingData.value.data!![0].lat
            viewModel.getMausam(lat = latitude.toString(), lon = longitude.toString())
        }
    }
    if(geocodingData.value.loading == true || mausamData.loading == true) {
        CircularProgressIndicator()
    } else if(geocodingData.value.e != null || mausamData.e != null) {
        // handle exceptions case
    } else {
        Text(text = mausamData.data!!.city.name)
    }
}