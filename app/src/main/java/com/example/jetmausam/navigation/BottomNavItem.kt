package com.example.jetmausam.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jetmausam.navigation.MausamScreens

enum class BottomNavItem(val icon: ImageVector, val label: String, val route: String) {
    Home(Icons.Default.Home, "Home", route = MausamScreens.MainScreen.name),
    Search(Icons.Default.Search, "Search", route = MausamScreens.SearchScreen.name),
    Favourites(Icons.Default.Favorite, "Favourites", route = MausamScreens.FavoriteScreen.name),
    Profile(Icons.Default.Person, "Profile", route = MausamScreens.AboutScreen.name)
}