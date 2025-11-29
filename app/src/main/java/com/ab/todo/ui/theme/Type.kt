package com.ab.todo.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.ab.todo.R


val cairoPlayFontFamily = FontFamily(
    Font(R.font.cairo_play_regular, FontWeight.Normal),
    Font(R.font.cairo_play_medium, FontWeight.Medium),
    Font(R.font.cairo_play_semi_bold, FontWeight.SemiBold),
    Font(R.font.cairo_play_bold, FontWeight.Bold),
    Font(R.font.cairo_play_extra_bold, FontWeight.ExtraBold),
    Font(R.font.cairo_play_black, FontWeight.Black),
    Font(R.font.cairo_play_light, FontWeight.Light),
)

// Default Material 3 typography values
val baseline = Typography()

val CairoPlayTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = cairoPlayFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = cairoPlayFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = cairoPlayFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = cairoPlayFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = cairoPlayFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = cairoPlayFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = cairoPlayFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = cairoPlayFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = cairoPlayFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = cairoPlayFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = cairoPlayFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = cairoPlayFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = cairoPlayFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = cairoPlayFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = cairoPlayFontFamily),
)