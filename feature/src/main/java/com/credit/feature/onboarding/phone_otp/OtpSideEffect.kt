package com.credit.feature.onboarding.phone_otp

sealed interface OtpSideEffect {
    data object NavigateToHome : OtpSideEffect
    data class ShowError(val message: String) : OtpSideEffect
}
