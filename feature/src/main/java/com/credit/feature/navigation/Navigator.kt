package com.credit.feature.navigation

import androidx.compose.runtime.staticCompositionLocalOf

interface Navigator {
    fun navigate(destination: ScreenDestination)
}

val LocalNavigator = staticCompositionLocalOf<Navigator> {
    error("LocalNavigator not provided. Wrap content in CreditNavHost.")
}
