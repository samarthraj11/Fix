package com.credit.feature.onboarding

sealed interface LanguageSideEffect {
    data object NavigateToPhoneNumber : LanguageSideEffect
}
