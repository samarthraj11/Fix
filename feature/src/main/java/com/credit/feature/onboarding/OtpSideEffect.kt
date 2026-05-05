package com.credit.feature.onboarding

sealed interface OtpSideEffect {
    data object NavigateToHome : OtpSideEffect
    data class ShowError(val message: String) : OtpSideEffect
}
