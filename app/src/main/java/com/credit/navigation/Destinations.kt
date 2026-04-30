package com.credit.navigation

sealed class Destination(val route: String) {
    data object Language : Destination("language")
    data object PhoneNumber : Destination("phone_number")
    data object Otp : Destination("otp/{phoneNumber}") {
        fun route(phoneNumber: String) = "otp/$phoneNumber"
        const val ARG_PHONE = "phoneNumber"
    }
    data object Home : Destination("home")
}
