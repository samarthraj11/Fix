package com.credit.designsystem.tokens

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Composition local for the full semantic color set, including extended
 * roles (e.g. *-fixed, surface-container-*) that aren't in M3 [ColorScheme].
 * Read via `CreditTheme.colors` from theme.
 */
val LocalCreditColors = staticCompositionLocalOf<CreditSemanticColors> {
    error("CreditTheme not provided. Wrap your content in CreditTheme { ... }.")
}
