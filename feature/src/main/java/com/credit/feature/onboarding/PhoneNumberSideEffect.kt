package com.credit.feature.onboarding

sealed interface PhoneNumberSideEffect {
    data class NavigateToOtp(val phoneNumber: String) : PhoneNumberSideEffect
    data class ShowError(val message: String) : PhoneNumberSideEffect
}
