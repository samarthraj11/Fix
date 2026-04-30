package com.credit.designsystem.tokens

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.credit.designsystem.tokens.BaseColors as B

/**
 * Semantic color tokens — role-based names that reference [BaseColors].
 * Components and screens should consume these (or the Material [ColorScheme]
 * derived from them), never the raw base tokens.
 */
@Immutable
data class CreditSemanticColors(
    // Primary
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val inversePrimary: Color,
    val primaryFixed: Color,
    val primaryFixedDim: Color,
    val onPrimaryFixed: Color,
    val onPrimaryFixedVariant: Color,

    // Secondary
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val secondaryFixed: Color,
    val secondaryFixedDim: Color,
    val onSecondaryFixed: Color,
    val onSecondaryFixedVariant: Color,

    // Tertiary
    val tertiary: Color,
    val onTertiary: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,
    val tertiaryFixed: Color,
    val tertiaryFixedDim: Color,
    val onTertiaryFixed: Color,
    val onTertiaryFixedVariant: Color,

    // Error
    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,

    // Background / Surface
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val surfaceDim: Color,
    val surfaceBright: Color,
    val surfaceContainerLowest: Color,
    val surfaceContainerLow: Color,
    val surfaceContainer: Color,
    val surfaceContainerHigh: Color,
    val surfaceContainerHighest: Color,
    val surfaceTint: Color,
    val inverseSurface: Color,
    val inverseOnSurface: Color,

    // Outline
    val outline: Color,
    val outlineVariant: Color,
)

val LightSemanticColors = CreditSemanticColors(
    // Primary
    primary = B.Green40,
    onPrimary = B.White,
    primaryContainer = B.Green90,
    onPrimaryContainer = B.Green10,
    inversePrimary = B.Green80,
    primaryFixed = B.Green95,
    primaryFixedDim = B.Green80,
    onPrimaryFixed = B.Green10,
    onPrimaryFixedVariant = B.Green30,

    // Secondary
    secondary = B.SecondaryPurple40,
    onSecondary = B.White,
    secondaryContainer = B.SecondaryPurple90,
    onSecondaryContainer = B.SecondaryPurple50,
    secondaryFixed = B.SecondaryPurple95,
    secondaryFixedDim = B.SecondaryPurple85,
    onSecondaryFixed = B.SecondaryPurple15,
    onSecondaryFixedVariant = B.SecondaryPurple30,

    // Tertiary (gold)
    tertiary = B.Gold40,
    onTertiary = B.White,
    tertiaryContainer = B.Gold70,
    onTertiaryContainer = B.Gold20,
    tertiaryFixed = B.Gold90,
    tertiaryFixedDim = B.Gold80,
    onTertiaryFixed = B.Gold10,
    onTertiaryFixedVariant = B.Gold30,

    // Error
    error = B.Red40,
    onError = B.White,
    errorContainer = B.Red90,
    onErrorContainer = B.Red20,

    // Background / Surface
    background = B.Neutral98,
    onBackground = B.Neutral10,
    surface = B.Neutral98,
    onSurface = B.Neutral10,
    surfaceVariant = B.Neutral90,
    onSurfaceVariant = B.Neutral30,
    surfaceDim = B.Neutral85,
    surfaceBright = B.Neutral98,
    surfaceContainerLowest = B.White,
    surfaceContainerLow = B.Neutral96,
    surfaceContainer = B.Neutral94,
    surfaceContainerHigh = B.Neutral92,
    surfaceContainerHighest = B.Neutral90,
    surfaceTint = B.Green40,
    inverseSurface = B.Neutral20,
    inverseOnSurface = B.Neutral97,

    // Outline
    outline = B.Neutral60,
    outlineVariant = B.Neutral80,
)
