package com.example.jetmausam.utils

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.example.jetmausam.R

object MyFonts {

    val alegreyaSansFamily = FontFamily(
        Font(R.font.alegreyasan_bold, FontWeight.Bold),
        Font(R.font.alegreyasans_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.alegreyasans_medium, FontWeight.Medium),
        Font(R.font.alegreyasans_regular, FontWeight.Normal)
    )
}