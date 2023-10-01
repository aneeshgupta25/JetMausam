package com.example.jetmausam.screens.about

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetmausam.navigation.CustomBottomNavigation
import com.example.jetmausam.navigation.MausamScreens
import com.example.jetmausam.utils.MyFonts
import com.example.jetmausam.widgets.CommonAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(
        topBar = { CommonAppBar(
            title = "About",
            navController = navController
        ) },
        bottomBar = { CustomBottomNavigation(navController = navController) }
    ) {
        Box(
            modifier = Modifier.padding(it),
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Made with ❤️ by Aneesh Gupta..",
                    fontFamily = MyFonts.alegreyaSansFamily,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            BackHandler(true) {
                navController.popBackStack(route = MausamScreens.MainScreen.name, inclusive = false)
            }
        }
    }
}