package com.credit.feature.onboarding

sealed interface OnboardingSideEffect {
    data object NavigateToHome : OnboardingSideEffect
    data class ShowError(val message: String) : OnboardingSideEffect
}
