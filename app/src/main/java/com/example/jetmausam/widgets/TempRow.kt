package com.example.jetmausam.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetmausam.utils.MyFonts

@Composable
fun TempRow(
    modifier: Modifier = Modifier,
    valueFontSize: TextUnit,
    unitFontSize: TextUnit,
    valueText: String,
    unitInCel: Boolean
) {
    Row(
        modifier = modifier
    ) {
        Text(text = valueText,
            fontFamily = MyFonts.alegreyaSansFamily,
            fontSize = valueFontSize,
            fontWeight = FontWeight.Bold,
            style = LocalTextStyle.current.merge(
                TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                )
            )
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = if(unitInCel) "°C" else "F",
            fontFamily = MyFonts.alegreyaSansFamily,
            fontSize = unitFontSize,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.labelLarge)
    }
}