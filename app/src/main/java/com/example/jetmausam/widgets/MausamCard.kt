package com.example.jetmausam.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.example.jetmausam.R
import com.example.jetmausam.utils.MyFonts
import com.example.jetmausam.utils.UTCtoISTFormatter

@Preview
@Composable
fun MausamCard(
    modifier: Modifier = Modifier.fillMaxSize(),
    stateAndCountry: String = "Delhi, IN",
    utcTime: Long = 0,
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
            shape = RoundedCornerShape(corner = CornerSize(35.dp)),
            colors = CardDefaults.cardColors(Color.White)
        ) {

        }

        Image(painter = painterResource(id = R.drawable.hello), contentDescription = "Image",
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                bottom.linkTo(imageBottomGuideline)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            })

        Card(
            modifier = Modifier.clickable {onClick.invoke()}.constrainAs(button) {
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
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(text = stateAndCountry,
                    fontFamily = MyFonts.alegreyaSansFamily,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black)

                TempRow(
                    valueFontSize = 50.sp,
                    unitFontSize = 20.sp,
                    valueText = "15",
                    unitInCel = true
                )

                Text(text = UTCtoISTFormatter.formatUTCtoIST(utcTime),
                    fontFamily = MyFonts.alegreyaSansFamily,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Normal,
                    color = Color.LightGray)

//                Text(text = buildAnnotatedString {
//                    withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
//                        withStyle(style = SpanStyle(
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 50.sp
//                        )) {
//                            append("15")
//                        }
//                        withStyle(style = SpanStyle(
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 20.sp
//                        )) {
//                            append("C")
//                        }
//                    }
//                },
//                    modifier = Modifier.weight(0.8f),
//                    textAlign = TextAlign.)

            }
        }
    }
}