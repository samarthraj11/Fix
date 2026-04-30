package com.credit.feature.onboarding

data class OnboardingState(
    val phoneNumber: String = "",
    val isSubmitting: Boolean = false,
    val error: String? = null
) {
    val isValid: Boolean get() = phoneNumber.length in 7..15 && phoneNumber.all { it.isDigit() }
}
