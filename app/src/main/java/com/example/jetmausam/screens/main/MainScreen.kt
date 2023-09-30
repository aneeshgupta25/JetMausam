package com.example.jetmausam.screens.main

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetmausam.MainActivity
import com.example.jetmausam.R
import com.example.jetmausam.data.DataOrException
import com.example.jetmausam.navigation.CustomBottomNavigation
import com.example.jetmausam.navigation.MausamScreens
import com.example.jetmausam.utils.AppConstants
import com.example.jetmausam.utils.MyFonts
import com.example.jetmausam.widgets.MausamAppBar
import com.example.jetmausam.widgets.MausamCard
import java.time.Instant

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navController: NavController,
    activity: MainActivity
//    city: String?
) {

    val sevenDaysMausamData = viewModel.sevenDaysMausamData.value
    if(viewModel.cityChange.value) {
        viewModel.cityChangeReceived()
        LaunchedEffect(key1 = viewModel.city.value) {
            viewModel.getMausam(viewModel.city.value)
        }
    }
    if(sevenDaysMausamData.loading == true) {
        CircularProgressIndicator()
    } else if(sevenDaysMausamData.e != null) {

    } else {
        MainScaffold(viewModel = viewModel, navController = navController, activity = activity)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun MainScaffold(
    viewModel: MainViewModel,
    navController: NavController,
    activity: MainActivity
) {
    var sevenDaysMausamData = viewModel.sevenDaysMausamData.value
    Scaffold(
        topBar = { MausamAppBar(
            viewModel = viewModel,
            navController = navController
        ) },
        containerColor = Color.Transparent,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    AppConstants.Pink,
                    AppConstants.Purple
                )
            )
        ),
        bottomBar = { CustomBottomNavigation(navController = navController) }
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
                    stateAndCountry = "${sevenDaysMausamData.data!!.city.name}, ${sevenDaysMausamData.data!!.city.country}",
                    tempValue = sevenDaysMausamData.data!!.list[0].temp.day,
                    utcTime = Instant.now().toEpochMilli(),
                    imgId = viewModel.getCustomImageOfMausam(defaultId = sevenDaysMausamData.data!!.list[0].weather[0].icon),
                ) {
                    navController.navigate(MausamScreens.StatsScreen.name)
                }
                Spacer(modifier = Modifier.height(15.dp))
                MausamInfoSurface(modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(0.6f),
                    cloudinessValue = sevenDaysMausamData.data!!.list[0].clouds,
                    humidityValue = sevenDaysMausamData.data!!.list[0].humidity,
                    windSpeedValue = sevenDaysMausamData.data!!.list[0].speed
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
    BackHandler(true) {
        activity.finish()
    }
}


@Composable
fun MausamInfoSurface(
    modifier: Modifier = Modifier,
    humidityValue: Int = 10,
    windSpeedValue: Double = 10.0,
    cloudinessValue: Int = 10
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MausamInfoRow(
            modifier = Modifier.weight(1f),
            imageResource = R.drawable.rain_info,
            text = "Cloudiness",
            value = "$cloudinessValue%"
        )
        MausamInfoRow(
            modifier = Modifier.weight(1f),
            imageResource = R.drawable.humid_info,
            text = "Humidity",
            value = "$humidityValue%"
        )
        MausamInfoRow(
            modifier = Modifier.weight(1f),
            imageResource = R.drawable.wind_info,
            text = "Wind Speed",
            value = "${windSpeedValue}m/sec"
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
        Image(
            painter = painterResource(id = imageResource), contentDescription = "",
            modifier = Modifier
                .weight(0.2f),
            contentScale = ContentScale.FillHeight
        )
        Box(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontFamily = MyFonts.alegreyaSansFamily,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }
        Box(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontFamily = MyFonts.alegreyaSansFamily,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
            )
        }
    }
}