package com.credit.feature.onboarding.language

sealed interface LanguageSideEffect {
    data object NavigateToPhoneNumber : LanguageSideEffect
}
