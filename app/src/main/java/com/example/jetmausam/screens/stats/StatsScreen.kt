package com.example.jetmausam.screens.stats

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetmausam.R
import com.example.jetmausam.screens.main.MainViewModel
import com.example.jetmausam.utils.MyFonts
import com.example.jetmausam.utils.Utils
import com.example.jetmausam.utils.UTCtoISTFormatter
import com.example.jetmausam.widgets.FutureMausamRow
import com.example.jetmausam.widgets.MausamCard
import com.example.jetmausam.widgets.MausamInfoCard
import com.example.jetmausam.widgets.TempRow

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun StatsScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    var sevenDaysMausamData = viewModel.sevenDaysMausamData.value
    var currentDayMausamData = viewModel.currentDayMausamData.value
    val screenWidth = Utils.getScreenWidth()
    Scaffold(
        topBar = {

        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Surface(
                    modifier = Modifier
                        .weight(3f)
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                ) {
                    Image(painter = painterResource(id = R.drawable.hello),
                        contentDescription = "",
                        modifier = Modifier.graphicsLayer(alpha = 0.5f),
                        contentScale = ContentScale.FillHeight)
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        TopAppBar(title = {},
                            navigationIcon = {
                                Icon(imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "",
                                    tint = Color.Black)
                            },
                            colors = TopAppBarDefaults.smallTopAppBarColors(Color.Transparent))
                        Text(
                            text = "${sevenDaysMausamData.data!!.city.name},",
                            fontSize = 25.sp,
                            fontFamily = MyFonts.alegreyaSansFamily,
                            fontWeight = FontWeight.Bold,)
                        Text(
                            text = sevenDaysMausamData.data!!.city.country,
                            fontSize = 25.sp,
                            fontFamily = MyFonts.alegreyaSansFamily,
                            fontWeight = FontWeight.Bold,)
                        Spacer(modifier = Modifier.weight(1f))
                        TempRow(
                            valueFontSize = 80.sp,
                            unitFontSize = 20.sp,
                            valueText = sevenDaysMausamData.data!!.list[0].temp.day.toString(),
                            unitInCel = true
                        )
                    }
                }
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .fillMaxHeight(),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var cardModifier: Modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                        MausamInfoCard(modifier = cardModifier,
                            imageResource = R.drawable.rain_info_col,
                            value = "6%",
                            backgroundColor = Color(0xFF658ED9).copy(alpha = 0.1f),
                            contentColor = Color(0xFF658ED9))
                        MausamInfoCard(modifier = cardModifier,
                            imageResource = R.drawable.humid_info_col,
                            value = "90%",
                            backgroundColor = Color(0xFFD86191).copy(alpha = 0.1f),
                            contentColor = Color(0xFFD86191))
                        MausamInfoCard(modifier = cardModifier,
                            imageResource = R.drawable.wind_info_col,
                            value = "6 km/h",
                            backgroundColor = Color(0xFF5E4FC1).copy(alpha = 0.1f),
                            contentColor = Color(0xFF5E4FC1))
                    }
                }
                Surface(
                    modifier = Modifier
                        .weight(3.5f)
                        .fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Today (${viewModel.getCustomDayWeek(0)})",
                            fontSize = 25.sp,
                            fontFamily = MyFonts.alegreyaSansFamily,
                            fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(10.dp))
                        LazyRow(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(),
                            contentPadding = PaddingValues(5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            items(currentDayMausamData.data!!.list) {
                                Log.d("Aneesh", "StatsScreen: ${it.dt_txt}")
                                MausamCard(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(screenWidth / 4),
                                    utcTime = "${it.dt_txt[11]}${it.dt_txt[12]}".toLong(),
                                    tempValue = it.main.temp,
                                    tempUnit = 30.sp,
                                    otherTextUnit = 18.sp,
                                    cornerRadius = 20.dp,
                                    canNavigateToNextScreen = false
                                )
                            }
                        }
                    }
                }
                Surface(
                    modifier = Modifier
                        .weight(2.5f)
                        .fillMaxWidth(),
                ) {
                    LazyColumn(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        contentPadding = PaddingValues(vertical = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        itemsIndexed(sevenDaysMausamData.data!!.list) {index, it ->
                            FutureMausamRow(
                                valueFontSize = 25.sp,
                                unitFontSize = 15.sp,
                                day = viewModel.getCustomDayWeek(index),
                                minTemp = it.temp.min,
                                maxTemp = it.temp.max,
                                unitInCel = true
                            )
                        }
                    }
                }
            }
        }
    }
}