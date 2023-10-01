package com.example.jetmausam.screens.settings

import android.graphics.fonts.FontFamily
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetmausam.model.db.Unit
import com.example.jetmausam.utils.AppConstants
import com.example.jetmausam.utils.MyFonts
import com.example.jetmausam.widgets.CommonAppBar

@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun SettingsScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel
) {
    Scaffold(
        topBar = { CommonAppBar(title = "Settings",
            navController = navController) }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Change Units of Measurement",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontFamily = MyFonts.alegreyaSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                )
                Spacer(modifier = Modifier.height(15.dp))
                Button(onClick = {
                    settingsViewModel.toggleSettingsText()
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppConstants.Pink
                    ) ,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .clip(shape = CircleShape)
                        .padding(5.dp)) {
                    Text(text = if(settingsViewModel.settingsTextFlag.value == 0) "Celcius °C" else "Fahrenheit F",
                        fontFamily = MyFonts.alegreyaSansFamily,
                        color = Color.White,
                        fontSize = 24.sp
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Button(onClick = {
                    settingsViewModel.saveUpdatedSettings()
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppConstants.Purple
                    )) {
                    Text(text = "SAVE",
                        fontFamily = MyFonts.alegreyaSansFamily,
                        fontSize = 18.sp,
                        color = Color.White)
                }

            }
        }
    }
}