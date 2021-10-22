package com.molol.mechasearch.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.molol.mechasearch.R

private val ProximaNova = FontFamily(
    Font(R.font.proxima_nova),
    //Font(R.font.montserrat_medium, FontWeight.W500),
    //Font(R.font.montserrat_semibold, FontWeight.W600)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = ProximaNova, //FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    h1 = TextStyle(
        fontFamily = ProximaNova,
        //fontWeight = FontWeight.W600,
        fontSize = 26.sp
    ),
    h2 = TextStyle(
        fontFamily = ProximaNova,
        //fontWeight = FontWeight.W600,
        fontSize = 18.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = ProximaNova,
        //fontWeight = FontWeight.W600,
        fontSize = 14.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = ProximaNova,
        //fontWeight = FontWeight.W500,
        fontSize = 12.sp
    )

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)