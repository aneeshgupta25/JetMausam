package com.example.jetmausam.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.jetmausam.R
import com.example.jetmausam.navigation.MausamScreens
import com.example.jetmausam.utils.AppConstants
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    AppConstants.Pink,
                    AppConstants.Purple
                )
            )
        )) {
        val scale = remember {
            Animatable(0f)
        }

        LaunchedEffect(key1 = true, block = {
            delay(200)
            scale.animateTo(targetValue = 0.9f,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = {
                        OvershootInterpolator(8f)
                            .getInterpolation(it)
                    }
                )
            )
            delay(2000)
            navController.navigate(MausamScreens.MainScreen.name+"/Delhi,IN")
        })

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "app logo",
                modifier = Modifier.fillMaxHeight(0.2f).scale(scale.value),
                contentScale = ContentScale.FillHeight)
            Text(text = "Jet Mausam",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White)
        }
    }
}