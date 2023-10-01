package com.example.jetmausam.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetmausam.MainActivity
import com.example.jetmausam.screens.about.AboutScreen
import com.example.jetmausam.screens.fav.FavViewModel
import com.example.jetmausam.screens.fav.FavouritesScreen
import com.example.jetmausam.screens.main.MainScreen
import com.example.jetmausam.screens.main.MainViewModel
import com.example.jetmausam.screens.search.SearchScreen
import com.example.jetmausam.screens.settings.SettingsScreen
import com.example.jetmausam.screens.settings.SettingsViewModel
import com.example.jetmausam.screens.splash.SplashScreen
import com.example.jetmausam.screens.stats.StatsScreen

@Composable
fun MausamNavigation(
    activity: MainActivity
) {
    val navController = rememberNavController()
    val mainViewModel = hiltViewModel<MainViewModel>()
    val favViewModel = hiltViewModel<FavViewModel>()
    val settingsViewModel = hiltViewModel<SettingsViewModel>()
    NavHost(
        navController = navController,
        startDestination = MausamScreens.SplashScreen.name
    ) {
        composable(route = MausamScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
//        var route = MausamScreens.MainScreen.name
//        composable(route = "$route/{city}",
//            arguments = listOf(
//                navArgument("city") { type = NavType.StringType }
//            )
//        ) { navArgument ->
//            var city = navArgument.arguments?.getString("city")
//            MainScreen(navController = navController, viewModel = mainViewModel, city = city)
//        }
        composable(route = MausamScreens.MainScreen.name) {
            MainScreen(mainViewModel = mainViewModel, navController = navController, activity = activity,
                favViewModel = favViewModel, settingsViewModel = settingsViewModel)
        }
        composable(route = MausamScreens.StatsScreen.name) {
            StatsScreen(viewModel = mainViewModel, settingsViewModel = settingsViewModel, navController = navController)
        }
        composable(route = MausamScreens.SearchScreen.name) {
            SearchScreen(viewModel = mainViewModel, navController = navController)
        }
        composable(route = MausamScreens.AboutScreen.name) {
            AboutScreen(navController = navController)
        }
        composable(route = MausamScreens.FavoriteScreen.name) {
            FavouritesScreen(navController = navController, favViewModel = favViewModel,
                mainViewModel = mainViewModel)
        }
        composable(route = MausamScreens.SettingsScreen.name) {
//            SettingsScreen(navController = navController, settingsViewModel = settingsViewModel)
            SettingsScreen(navController = navController, settingsViewModel = settingsViewModel)
        }
    }
}