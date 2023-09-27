package com.example.jetmausam.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetmausam.R
import com.example.jetmausam.utils.MyFonts

@Composable
@Preview
fun MausamInfoCard(
    modifier: Modifier = Modifier,
    imageResource: Int = R.drawable.humid_info_col,
    value: String = "6%",
    backgroundColor: Color = Color(0xFFD86191).copy(alpha = 0.1f),
    contentColor: Color = Color(0xFFD86191)
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.5f)
                .clip(RoundedCornerShape(corner = CornerSize(20.dp)))
                .background(color = backgroundColor),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = imageResource), contentDescription = "",
                modifier = Modifier.weight(2f).fillMaxHeight(0.9f),
                contentScale = ContentScale.Fit)
            Text(
                text = value,
                color = contentColor,
                modifier = Modifier.weight(3f),
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                fontFamily = MyFonts.alegreyaSansFamily,
                fontWeight = FontWeight.Bold
            )
        }
    }
}