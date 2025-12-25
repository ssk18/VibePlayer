package com.ssk.vibeplayer.core.presentation.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ssk.vibepalyer.core.presentation.designsystem.R

// Set of Material typography styles to start with
@OptIn(ExperimentalTextApi::class)
val HostGroteskRegular = FontFamily(
    androidx.compose.ui.text.font.Font(
        R.font.hostgrotesk_variablefont_wght,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(400)
        )
    )
)

@OptIn(ExperimentalTextApi::class)
val HostGroteskMedium = FontFamily(
    androidx.compose.ui.text.font.Font(
        R.font.hostgrotesk_variablefont_wght,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(500)
        )
    )
)

@OptIn(ExperimentalTextApi::class)
val HostGroteskSemiBold = FontFamily(
    androidx.compose.ui.text.font.Font(
        R.font.hostgrotesk_variablefont_wght,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(600)
        )
    )
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = HostGroteskMedium,
        fontSize = 28.sp,
        lineHeight = 32.sp
    ),
    titleMedium = TextStyle(
        fontFamily = HostGroteskSemiBold,
        fontSize = 20.sp,
        lineHeight = 24.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = HostGroteskRegular,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = HostGroteskMedium,
        fontSize = 16.sp,
        lineHeight = 22.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = HostGroteskRegular,
        fontSize = 14.sp,
        lineHeight = 18.sp,
    )
)
