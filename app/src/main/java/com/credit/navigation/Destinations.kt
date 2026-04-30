package com.credit.navigation

sealed class Destination(val route: String) {
    data object Language : Destination("language")
    data object Onboarding : Destination("onboarding")
    data object Home : Destination("home")
}
