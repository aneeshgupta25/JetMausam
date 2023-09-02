package com.example.jetmausam.screens.main

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    if(viewModel.geocodingData.value.loading == true || viewModel.mausamData.value.loading == true) {
        CircularProgressIndicator()
    } else {
        Text(text = viewModel.mausamData.value.data!!.city.country)
    }
}