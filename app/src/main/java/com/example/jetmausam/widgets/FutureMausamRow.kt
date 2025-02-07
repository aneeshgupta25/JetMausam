package com.example.jetmausam.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetmausam.R
import com.example.jetmausam.utils.MyFonts

@Composable
@Preview
fun FutureMausamRow(
    modifier: Modifier = Modifier,
    valueFontSize: TextUnit = 35.sp,
    unitFontSize: TextUnit = 20.sp,
    day: String = "Monday",
    imgId: Int = R.drawable.rain_d,
    minTemp: Double = 0.0,
    maxTemp: Double = 0.0,
    unitInCel: Boolean = true
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(modifier = Modifier.weight(0.35f),
            text = day,
            fontFamily = MyFonts.alegreyaSansFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Box(
            modifier = Modifier.weight(0.35f).height(30.dp).background(Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(id = imgId), contentDescription = "",
                contentScale = ContentScale.FillHeight)
        }
        TempRow(
            modifier = Modifier.weight(0.15f),
            valueFontSize = valueFontSize, unitFontSize = unitFontSize, valueText = minTemp.toInt().toString(), unitInCel = unitInCel)
        TempRow(
            modifier = Modifier.weight(0.15f),
            valueFontSize = valueFontSize, unitFontSize = unitFontSize, valueText = maxTemp.toInt().toString(), unitInCel = unitInCel)
    }
}