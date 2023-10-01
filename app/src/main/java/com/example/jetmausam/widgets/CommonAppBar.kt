package com.example.jetmausam.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jetmausam.navigation.MausamScreens
import com.example.jetmausam.utils.MyFonts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonAppBar(
    title: String = "",
    isStatsScreen: Boolean = false,
    navController: NavController,
    onBackPress: ()->Unit = {
        navController.popBackStack(route = MausamScreens.MainScreen.name, inclusive = false)
    }
) {
    TopAppBar(title = {
        if(isStatsScreen) {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                fontFamily = MyFonts.alegreyaSansFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Transparent
        ),
        navigationIcon = {
            IconButton(onClick = { onBackPress.invoke() }) {
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
}