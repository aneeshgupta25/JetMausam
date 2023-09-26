package com.example.jetmausam.screens.stats

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.jetmausam.model.mausam.MausamDailyData
import com.example.jetmausam.screens.main.MainViewModel
import com.example.jetmausam.utils.MyFonts
import com.example.jetmausam.widgets.MausamCard
import com.example.jetmausam.widgets.TempRow

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun StatsScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
//    var dailyMausamList = viewModel.dailyMausamListState.value
    var mausamData = viewModel.mausamData.value
    Scaffold(
        topBar = {
            TopAppBar(title = {},
                modifier = Modifier.padding(horizontal = 15.dp),
                navigationIcon = {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        tint = Color.Black)
                })
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Surface(
                    modifier = Modifier
                        .weight(0.3f)
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                ) {
                    Image(painter = painterResource(id = R.drawable.hello),
                        contentDescription = "",
                        modifier = Modifier.graphicsLayer(alpha = 0.5f),
                        contentScale = ContentScale.FillHeight)
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = mausamData.data!!.city.name,
                            modifier = Modifier.weight(0.4f),
                            fontSize = 30.sp,
                            fontFamily = MyFonts.alegreyaSansFamily,
                            fontWeight = FontWeight.Bold,)
                        TempRow(
                            modifier = Modifier.weight(0.6f),
                            valueFontSize = 90.sp,
                            unitFontSize = 30.sp,
                            valueText = "15",
                            unitInCel = true
                        )
                    }
                }
                Surface(
                    modifier = Modifier
                        .weight(0.1f)
                        .fillMaxWidth(),
                ) {

                }
                Surface(
                    modifier = Modifier
                        .weight(0.2f)
                        .fillMaxWidth(),
                ) {
                    LazyRow(
                        modifier = Modifier.fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        items(mausamData.data!!.list) {
                            MausamCard(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(0.3f)
                            )
                        }
                    }
                }
                Surface(
                    modifier = Modifier
                        .weight(0.4f)
                        .fillMaxWidth(),
                ) {

                }
            }
        }
    }
}