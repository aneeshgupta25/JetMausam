package com.example.jetmausam.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.jetmausam.R
import com.example.jetmausam.screens.main.MainViewModel
import com.example.jetmausam.utils.Utils

//@Preview
@Composable
fun MausamAppBar(
    viewModel: MainViewModel,
    navController: NavController
) {
    val screenHeight = Utils.getScreenHeight()
    if(viewModel.dropDownDialogVisibility.value) {
        CustomDropDownMenu(viewModel = viewModel, navController = navController)
    }
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
        IconButton(onClick = { viewModel.toggleDropDownDialog() }) {
            Surface(
                shape = CircleShape,
                color = Color.Transparent
            ) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Settings",
                    tint = Color.White)
            }
        }
    }
}

@Composable
fun CustomDropDownMenu(viewModel: MainViewModel, navController: NavController) {
    val items = listOf(
        Pair("Settings", Icons.Default.Settings)
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        DropdownMenu(expanded = viewModel.dropDownDialogVisibility.value,
            onDismissRequest = { viewModel.toggleDropDownDialog() },
            modifier = Modifier
                .width(120.dp)
                .background(Color.White)) {
            items.forEach {
                DropdownMenuItem( text = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(imageVector = it.second, contentDescription = "")
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = it.first)
                    }
                }, onClick = { viewModel.toggleDropDownDialog() })
            }
        }
    }
}