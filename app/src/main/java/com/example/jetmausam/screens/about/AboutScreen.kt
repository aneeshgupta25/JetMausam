package com.example.jetmausam.screens.about

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.jetmausam.navigation.CustomBottomNavigation
import com.example.jetmausam.navigation.MausamScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(
        bottomBar = { CustomBottomNavigation(navController = navController) }
    ) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            Text(text = "Aneesh")
            BackHandler(true) {
                navController.popBackStack(route = MausamScreens.MainScreen.name, inclusive = false)
            }
        }
    }
}