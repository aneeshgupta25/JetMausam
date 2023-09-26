package com.example.jetmausam.screens.main

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetmausam.R
import com.example.jetmausam.data.DataOrException
import com.example.jetmausam.model.geocoding.GeoCodingData
import com.example.jetmausam.model.mausam.MausamData
import com.example.jetmausam.navigation.MausamScreens
import com.example.jetmausam.utils.AppConstants
import com.example.jetmausam.utils.MyFonts
import com.example.jetmausam.widgets.MausamAppBar
import com.example.jetmausam.widgets.MausamCard
import java.time.Instant

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
//    val geocodingData = produceState<DataOrException<GeoCodingData, Boolean, Exception>>(
//        initialValue = DataOrException(loading = true)
//    ) {
//        value = viewModel.getCoordinates("Delhi, IN")
//    }
//    val mausamData = viewModel.mausamState.value
//    LaunchedEffect(key1 = geocodingData.value) {
//        if(geocodingData.value.loading != true) {
//            val longitude = geocodingData.value.data!![0].lon
//            val latitude = geocodingData.value.data!![0].lat
//            viewModel.getMausam(lat = latitude.toString(), lon = longitude.toString())
//        }
//    }
//    if(geocodingData.value.loading == true || mausamData.loading == true) {
//        CircularProgressIndicator()
//    } else if(geocodingData.value.e != null || mausamData.e != null) {
//        // handle exceptions case
//    } else {
//        MainScaffold(viewModel, navController)
//    }
//    viewModel.getMausam("Delhi, IN")
    val mausamData = viewModel.mausamData.value

    LaunchedEffect(key1 = Unit) {
        if(mausamData.loading == true) {
            viewModel.getMausam("Delhi, IN")
        }
    }
    if(mausamData.loading == true) {
        CircularProgressIndicator()
    } else if(mausamData.e != null) {

    } else {
        MainScaffold(viewModel = viewModel, navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun MainScaffold(
    viewModel: MainViewModel,
    navController: NavController
) {
    var mausamData = viewModel.mausamData.value
    Scaffold(
        topBar = { MausamAppBar() },
        containerColor = Color.Transparent,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    AppConstants.Pink,
                    AppConstants.Purple
                )
            )
        )
    ) {
        Box(modifier = Modifier
            .padding(it)
            .background(Color.Transparent)) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MausamCard(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight(0.6f),
                    stateAndCountry = "${mausamData.data!!.city.name}, ${mausamData.data!!.city.country}",
                    utcTime = Instant.now().toEpochMilli()
                ) {
                    navController.navigate(MausamScreens.StatsScreen.name)
                }
                Spacer(modifier = Modifier.height(15.dp))
                MausamInfoSurface(modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(0.6f))
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}


@Composable
fun MausamInfoSurface(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MausamInfoRow(
            modifier = Modifier.weight(1f),
            imageResource = R.drawable.rain_info,
            text = "Precipitation",
            value = "6%"
        )
        MausamInfoRow(
            modifier = Modifier.weight(1f),
            imageResource = R.drawable.humid_info,
            text = "Humidity",
            value = "90%"
        )
        MausamInfoRow(
            modifier = Modifier.weight(1f),
            imageResource = R.drawable.wind_info,
            text = "Wind",
            value = "19 km/h"
        )
    }
}
@Composable
fun MausamInfoRow(
    modifier: Modifier = Modifier,
    imageResource: Int,
    text: String,
    value: String
) {
    Row(modifier = modifier) {
        Image(painter = painterResource(id = imageResource), contentDescription = "",
            modifier = Modifier
                .weight(0.2f),
            contentScale = ContentScale.FillHeight)
        Box(modifier = Modifier
            .weight(0.5f)
            .fillMaxHeight(),
            contentAlignment = Alignment.Center) {
            Text(text = text,
                fontFamily = MyFonts.alegreyaSansFamily,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,)
        }
        Box(modifier = Modifier
            .weight(0.3f)
            .fillMaxHeight(),
            contentAlignment = Alignment.Center) {
            Text(text = value,
                fontFamily = MyFonts.alegreyaSansFamily,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,)
        }
    }
}