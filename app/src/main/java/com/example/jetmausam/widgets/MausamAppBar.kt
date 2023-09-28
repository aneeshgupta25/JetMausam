package com.example.jetmausam.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetmausam.R
import com.example.jetmausam.utils.Utils

@Preview
@Composable
fun MausamAppBar() {
    val screenHeight = Utils.getScreenHeight()
    Row(
        modifier = Modifier
            .fillMaxHeight(0.1f)
            .fillMaxWidth()
            .padding(start = 15.dp, end = 5.dp, top = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = R.drawable.app_logo), contentDescription = "Logo",
        modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight)
        IconButton(onClick = { /*TODO*/ }) {
            Surface(
                shape = CircleShape,
                color = Color.Transparent
            ) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings",
                    tint = Color.White)
            }
        }
    }
}
