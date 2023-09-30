package com.example.jetmausam.screens.search

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CardDefaults.shape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.util.TableInfo
import com.example.jetmausam.navigation.CustomBottomNavigation
import com.example.jetmausam.navigation.MausamScreens
import com.example.jetmausam.screens.main.MainViewModel
import com.example.jetmausam.utils.AppConstants
import com.example.jetmausam.utils.MyFonts
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//@Preview
fun SearchScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Search",
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = MyFonts.alegreyaSansFamily,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center)
            },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
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
        },
        bottomBar = {
            CustomBottomNavigation(navController = navController)
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            SearchBar {
                Log.d("TAG", "SearchScreen: $it")
                viewModel.updateCity(it)
                navController.navigate(MausamScreens.MainScreen.name)
            }
        }
        BackHandler(true) {
            navController.popBackStack(route = MausamScreens.MainScreen.name, inclusive = false)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    onSearch: (String)->Unit
) {
    Column {
        val searchQueryState = rememberSaveable { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current
        val valid = remember(searchQueryState.value) { searchQueryState.value.trim().isNotEmpty() }
        CommonTextField(
            valueState = searchQueryState,
            placeHolder = "Search",
            onAction = KeyboardActions {
                if(!valid) return@KeyboardActions
                keyboardController?.hide()
                onSearch(searchQueryState.value)
                searchQueryState.value = ""
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextField(valueState: MutableState<String>,
                    placeHolder: String,
                    keyboardType: KeyboardType = KeyboardType.Text,
                    imeAction: ImeAction = ImeAction.Next,
                    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(value = valueState.value, onValueChange = {
        valueState.value = it
    },
        label = { Text(text = "Search", fontFamily = MyFonts.alegreyaSansFamily) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        colors = TextFieldDefaults
            .outlinedTextFieldColors(
                focusedBorderColor = AppConstants.Purple,
                cursorColor = Color.Black
            ),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    )
}
