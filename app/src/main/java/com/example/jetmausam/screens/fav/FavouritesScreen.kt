package com.example.jetmausam.screens.fav

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetmausam.model.db.Favourites
import com.example.jetmausam.navigation.CustomBottomNavigation
import com.example.jetmausam.navigation.MausamScreens
import com.example.jetmausam.screens.main.MainViewModel
import com.example.jetmausam.utils.AppConstants
import com.example.jetmausam.utils.MyFonts
import com.example.jetmausam.utils.Utils
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(navController: NavController,
                     favViewModel: FavViewModel,
                     mainViewModel: MainViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Favorite Cities",
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = MyFonts.alegreyaSansFamily,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center)
            },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Navigate Back")
                    }
                },
                actions = {
                    Row {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft,
                            tint = Color.Transparent,
                            contentDescription = "Navigate Back")
                    }
                })
        },
        bottomBar = { CustomBottomNavigation(navController = navController) }
    ) {
        Box(modifier = Modifier.padding(it)) {
            val favList = favViewModel.favList.collectAsState().value
            LazyColumn {
                items(favList) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 10.dp),
                        shape = CircleShape.copy(topEnd = CornerSize(20.dp)),
                        color = AppConstants.Pink.copy(alpha = 0.2f)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Box(modifier = Modifier.weight(1f),
                                contentAlignment = Alignment.Center) {
                                Text(text = it.city.lowercase(Locale.ROOT).capitalize(Locale.ROOT),
                                    fontFamily = MyFonts.alegreyaSansFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            }
                            Box(modifier = Modifier.weight(1f),
                                contentAlignment = Alignment.Center) {
                                Text(text = it.country.uppercase(Locale.ROOT),
                                    fontFamily = MyFonts.alegreyaSansFamily,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 20.sp
                                )
                            }
                            Box(modifier = Modifier.weight(1f),
                                contentAlignment = Alignment.Center) {
                                IconButton(onClick = {

                                    // check if item to be deleted is current city or not
                                    if(mainViewModel.currentDayMausamData.value.data!!.city.name.lowercase()
                                        == it.city.lowercase(Locale.ROOT).trim() &&
                                        mainViewModel.currentDayMausamData.value.data!!.city.country.lowercase()
                                        == it.country.lowercase(Locale.ROOT).trim()) {
                                        mainViewModel.toggleAddedToFavs()
                                    }

                                    favViewModel.deleteFavCity(Favourites(it.city.lowercase(Locale.ROOT).trim(),
                                        it.country.lowercase(Locale.ROOT).trim()
                                    ))
                                }) {
                                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Fav City",
                                        tint = Color.Red)
                                }
                            }
                        }
                    }
                }
            }
        }
        BackHandler(true) {
            navController.popBackStack(route = MausamScreens.MainScreen.name, inclusive = false)
        }
    }
}