package com.credit.feature.onboarding.phone_number

sealed interface PhoneNumberSideEffect {
    data class NavigateToOtp(val phoneNumber: String) : PhoneNumberSideEffect
    data class ShowError(val message: String) : PhoneNumberSideEffect
}
