package com.credit.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.credit.designsystem.tokens.CreditSemanticColors
import com.credit.designsystem.tokens.LightSemanticColors
import com.credit.designsystem.tokens.LocalCreditColors

private fun CreditSemanticColors.toMaterialColorScheme() = lightColorScheme(
    primary = primary,
    onPrimary = onPrimary,
    primaryContainer = primaryContainer,
    onPrimaryContainer = onPrimaryContainer,
    inversePrimary = inversePrimary,
    secondary = secondary,
    onSecondary = onSecondary,
    secondaryContainer = secondaryContainer,
    onSecondaryContainer = onSecondaryContainer,
    tertiary = tertiary,
    onTertiary = onTertiary,
    tertiaryContainer = tertiaryContainer,
    onTertiaryContainer = onTertiaryContainer,
    error = error,
    onError = onError,
    errorContainer = errorContainer,
    onErrorContainer = onErrorContainer,
    background = background,
    onBackground = onBackground,
    surface = surface,
    onSurface = onSurface,
    surfaceVariant = surfaceVariant,
    onSurfaceVariant = onSurfaceVariant,
    surfaceTint = surfaceTint,
    inverseSurface = inverseSurface,
    inverseOnSurface = inverseOnSurface,
    outline = outline,
    outlineVariant = outlineVariant,
)

@Composable
fun CreditTheme(
    content: @Composable () -> Unit
) {
    val semantic = LightSemanticColors
    CompositionLocalProvider(LocalCreditColors provides semantic) {
        MaterialTheme(
            colorScheme = semantic.toMaterialColorScheme(),
            typography = Typography,
            content = content
        )
    }
}

object CreditTheme {
    val colors: CreditSemanticColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCreditColors.current
}
