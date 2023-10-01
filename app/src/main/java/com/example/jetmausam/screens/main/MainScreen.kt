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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetmausam.MainActivity
import com.example.jetmausam.R
import com.example.jetmausam.model.db.Unit
import com.example.jetmausam.navigation.CustomBottomNavigation
import com.example.jetmausam.navigation.MausamScreens
import com.example.jetmausam.screens.fav.FavViewModel
import com.example.jetmausam.screens.settings.SettingsViewModel
import com.example.jetmausam.utils.AppConstants
import com.example.jetmausam.utils.MyFonts
import com.example.jetmausam.widgets.MausamAppBar
import com.example.jetmausam.widgets.MausamCard
import java.time.Instant

@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    favViewModel: FavViewModel,
    settingsViewModel: SettingsViewModel,
    navController: NavController,
    activity: MainActivity
) {

    if(mainViewModel.cityChange.value || settingsViewModel.changeInSettings.value) {
        mainViewModel.cityChangeReceived()
        settingsViewModel.settingsUpdated()
        LaunchedEffect(key1 = true) {
            mainViewModel.getMausam(mainViewModel.city.value, unitInCel = settingsViewModel.unitInCel.value)
        }
    }
    val sevenDaysMausamData = mainViewModel.sevenDaysMausamData.value
    if(sevenDaysMausamData.loading == true) {
        CircularProgressIndicator()
    } else {
        MainScaffold(mainViewModel = mainViewModel, navController = navController, activity = activity,
             favViewModel = favViewModel, unitInCel = settingsViewModel.unitInCel.value,
            exception = sevenDaysMausamData.e != null
        )
    }
}

@Preview
@Composable
fun ExceptionResponse() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.exception),
                contentDescription = "City Not Found!",
                modifier = Modifier.fillMaxWidth())
            Text(text = "City Not Found!!",
                fontFamily = MyFonts.alegreyaSansFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
                )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun MainScaffold(
    mainViewModel: MainViewModel,
    navController: NavController,
    activity: MainActivity,
    favViewModel: FavViewModel,
    unitInCel: Boolean,
    exception: Boolean
) {
    var sevenDaysMausamData = mainViewModel.sevenDaysMausamData.value
    Scaffold(
        topBar = { MausamAppBar(
            mainViewModel = mainViewModel,
            navController = navController,
            favViewModel = favViewModel,
            onFavClick = {
                Pair(mainViewModel.currentDayMausamData.value.data!!.city.name.lowercase(),
                     mainViewModel.currentDayMausamData.value.data!!.city.country.lowercase())
            },
            exception = exception
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
        if(exception) {
            ExceptionResponse()
        } else {
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
                        imgId = mainViewModel.getCustomImageOfMausam(defaultId = sevenDaysMausamData.data!!.list[0].weather[0].icon),
                        unitInCel = unitInCel
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