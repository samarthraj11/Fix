package com.credit.designsystem.tokens

import androidx.compose.ui.graphics.Color

/**
 * Base color tokens — raw palette values.
 * These are the only place hex literals should appear.
 * Semantic tokens reference these by role.
 */
internal object BaseColors {

    val White = Color(0xFFFFFFFF)

    // Primary (purple) tonal scale
    val Purple20 = Color(0xFF22005D)
    val Purple30 = Color(0xFF4F378A)
    val Purple40 = Color(0xFF6750A4)
    val Purple80 = Color(0xFFCFBCFF)
    val Purple90 = Color(0xFFE0D2FF)
    val Purple95 = Color(0xFFE9DDFF)

    // Secondary (muted purple) tonal scale
    val SecondaryPurple15 = Color(0xFF1F1635)
    val SecondaryPurple30 = Color(0xFF4B4263)
    val SecondaryPurple40 = Color(0xFF63597C)
    val SecondaryPurple50 = Color(0xFF645A7D)
    val SecondaryPurple85 = Color(0xFFCDC0E9)
    val SecondaryPurple90 = Color(0xFFE1D4FD)

    // Tertiary (gold) tonal scale
    val Gold10 = Color(0xFF241A00)
    val Gold20 = Color(0xFF503D00)
    val Gold30 = Color(0xFF594400)
    val Gold40 = Color(0xFF765B00)
    val Gold70 = Color(0xFFC9A74D)
    val Gold80 = Color(0xFFE7C365)
    val Gold90 = Color(0xFFFFDF93)

    // Error tonal scale
    val Red40 = Color(0xFFBA1A1A)
    val Red20 = Color(0xFF93000A)
    val Red90 = Color(0xFFFFDAD6)

    // Neutral tonal scale
    val Neutral10 = Color(0xFF1D1B20)
    val Neutral20 = Color(0xFF322F35)
    val Neutral30 = Color(0xFF494551)
    val Neutral60 = Color(0xFF7A7582)
    val Neutral80 = Color(0xFFCBC4D2)
    val Neutral85 = Color(0xFFDED8E0)
    val Neutral90 = Color(0xFFE6E0E9)
    val Neutral92 = Color(0xFFECE6EE)
    val Neutral94 = Color(0xFFF2ECF4)
    val Neutral96 = Color(0xFFF8F2FA)
    val Neutral97 = Color(0xFFF5EFF7)
    val Neutral98 = Color(0xFFFDF7FF)
}
