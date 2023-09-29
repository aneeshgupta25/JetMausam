package com.example.jetmausam.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetmausam.screens.main.MainScreen
import com.example.jetmausam.screens.main.MainViewModel
import com.example.jetmausam.screens.search.SearchScreen
import com.example.jetmausam.screens.splash.SplashScreen
import com.example.jetmausam.screens.stats.StatsScreen

@Composable
fun MausamNavigation() {
    val navController = rememberNavController()
    val mainViewModel = hiltViewModel<MainViewModel>()
    NavHost(
        navController = navController,
        startDestination = MausamScreens.SplashScreen.name
    ) {
        composable(route = MausamScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        var route = MausamScreens.MainScreen.name
        composable(route = "$route/{city}",
            arguments = listOf(
                navArgument("city") { type = NavType.StringType }
            )
        ) { navArgument ->
            var city = navArgument.arguments?.getString("city")
            MainScreen(navController = navController, viewModel = mainViewModel, city = city)
        }
        composable(route = MausamScreens.StatsScreen.name) {
            StatsScreen(viewModel = mainViewModel)
        }
        composable(route = MausamScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
    }
}