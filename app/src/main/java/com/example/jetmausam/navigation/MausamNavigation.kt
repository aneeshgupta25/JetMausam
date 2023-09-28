package com.example.jetmausam.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetmausam.screens.main.MainScreen
import com.example.jetmausam.screens.main.MainViewModel
import com.example.jetmausam.screens.splash.SplashScreen
import com.example.jetmausam.screens.stats.StatsScreen

@Composable
fun MausamNavigation() {
    val navController = rememberNavController()
    val mainViewModel = hiltViewModel<MainViewModel>()
    NavHost(
        navController = navController,
        startDestination = MausamScreens.MainScreen.name
    ) {
        composable(route = MausamScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(route = MausamScreens.MainScreen.name) {
            MainScreen(navController = navController, viewModel = mainViewModel)
        }
        composable(route = MausamScreens.StatsScreen.name) {
            StatsScreen(viewModel = mainViewModel)
        }
    }
}