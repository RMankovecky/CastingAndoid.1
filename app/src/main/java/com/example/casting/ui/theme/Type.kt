package com.example.casting.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.casting.R

val inter = FontFamily(
    Font(R.font.inter_regular)
)

val CustomFontWeight550 = FontWeight(550)

val Typography = Typography(
    labelMedium = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = inter,
        fontWeight = CustomFontWeight550,
        fontSize = 14.sp,
        lineHeight = 17.sp,
        letterSpacing = 0.16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.01.sp
    )
)