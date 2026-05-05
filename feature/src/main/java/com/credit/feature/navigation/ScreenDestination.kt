package com.credit.feature.navigation

sealed interface ScreenDestination {
    data object Language : ScreenDestination
    data object PhoneNumber : ScreenDestination
    data class Otp(val phoneNumber: String) : ScreenDestination
    data object Home : ScreenDestination
    data object Back : ScreenDestination
}
