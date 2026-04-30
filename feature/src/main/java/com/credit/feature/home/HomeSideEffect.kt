package com.credit.feature.home

sealed interface HomeSideEffect {
    data class ShowError(val message: String) : HomeSideEffect
    data object NavigateToOnboarding : HomeSideEffect
}
