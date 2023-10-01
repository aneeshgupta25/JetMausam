package com.example.jetmausam.widgets

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.jetmausam.R
import com.example.jetmausam.utils.MyFonts
import com.example.jetmausam.utils.UTCtoISTFormatter

//@Preview
@Composable
fun  MausamCard(
    modifier: Modifier = Modifier.fillMaxSize(),
    stateAndCountry: String = "Delhi, IN",
    utcTime: Long = 0,
    tempValue: Double = 15.0,
    imgId: Int = R.drawable.clear_sky_d,
    tempUnit: TextUnit = 50.sp,
    otherTextUnit: TextUnit = 20.sp,
    cornerRadius: Dp = 35.dp,
    canNavigateToNextScreen: Boolean = true,
    unitInCel: Boolean,
    onClick: ()->Unit = {}
) {
    ConstraintLayout(
        modifier = modifier
    ) {

        val imageBottomGuideline = createGuidelineFromTop(0.5f)
        val cardTopGuideline = createGuidelineFromTop(0.3f)
        val cardBottomGuideline = createGuidelineFromBottom(0.1f)
        val buttonTopGuideline = createGuidelineFromBottom(0.15f)

        val (image, card, button, textContainer) = createRefs()

        Card(
            modifier = Modifier.constrainAs(card) {
                top.linkTo(cardTopGuideline)
                bottom.linkTo(cardBottomGuideline)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            },
            elevation = CardDefaults.cardElevation(5.dp),
            shape = RoundedCornerShape(corner = CornerSize(cornerRadius)),
            colors = CardDefaults.cardColors(Color.White)
        ) {

        }

        Image(painter = painterResource(id = imgId), contentDescription = "Image",
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                bottom.linkTo(imageBottomGuideline)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            })

        if(canNavigateToNextScreen) {
            Card(
                modifier = Modifier
                    .clickable { onClick.invoke() }
                    .constrainAs(button) {
                        top.linkTo(buttonTopGuideline)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)

                        height = Dimension.fillToConstraints
                        width = Dimension.percent(0.7f)
                    },
                shape = RoundedCornerShape(corner = CornerSize(35.dp)),
                colors = CardDefaults.cardColors(Color(0xFF5E4FC1))
            ) {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "VIEW STATS",
                        fontFamily = MyFonts.alegreyaSansFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        Surface(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .constrainAs(textContainer) {
                    top.linkTo(imageBottomGuideline)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(buttonTopGuideline)

                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
            color = Color.Transparent
        ) {
            if(canNavigateToNextScreen) {
                TextContentIfNavigable(
                    stateAndCountry = stateAndCountry,
                    otherTextUnit = otherTextUnit,
                    tempUnit = tempUnit,
                    utcTime = utcTime,
                    tempValue = tempValue.toString(),
                    unitInCel = unitInCel
                )
            } else {
                TextContentIfNotNavigable(
                    otherTextUnit = otherTextUnit,
                    tempUnit = tempUnit,
                    utcTime = utcTime,
                    tempValue = tempValue,
                    unitInCel = unitInCel
                )
            }
        }
    }
}

@Composable
fun TextContentIfNavigable(stateAndCountry: String,
                           tempValue: String,
                           unitInCel: Boolean,
                           otherTextUnit: TextUnit,
                           tempUnit: TextUnit,
                           utcTime: Long) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(text = stateAndCountry,
            fontFamily = MyFonts.alegreyaSansFamily,
            fontSize = otherTextUnit,
            fontWeight = FontWeight.Bold,
            color = Color.Black)

        TempRow(
            valueFontSize = tempUnit,
            unitFontSize = otherTextUnit,
            valueText = tempValue,
            unitInCel = unitInCel
        )

        Text(text = UTCtoISTFormatter.getDayTimeFromUST(utcTime),
            fontFamily = MyFonts.alegreyaSansFamily,
            fontSize = otherTextUnit,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF332821).copy(alpha = 0.5f))

    }
}

@Composable
fun TextContentIfNotNavigable(
    utcTime: Long,
    tempValue: Double,
    tempUnit: TextUnit,
    unitInCel: Boolean,
    otherTextUnit: TextUnit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TempRow(
            valueFontSize = tempUnit,
            unitFontSize = otherTextUnit,
            valueText = tempValue.toInt().toString(),
            unitInCel = unitInCel
        )
        Text(text = if(utcTime < 12) "$utcTime AM" else "${utcTime-12} PM",
            fontFamily = MyFonts.alegreyaSansFamily,
            fontSize = otherTextUnit,
            fontWeight = FontWeight.Normal,
            color = Color.LightGray)
    }
}