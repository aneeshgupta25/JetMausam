package com.example.jetmausam.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetmausam.screens.SplashScreen

@Composable
fun MausamNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MausamScreens.SplashScreen.name
    ) {
        composable(route = MausamScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
    }
}