package com.credit.feature.onboarding.phone_number

data class PhoneNumberState(
    val phoneNumber: String = "",
    val hasConsent: Boolean = false,
    val isSubmitting: Boolean = false,
) {
    val isValid: Boolean get() = phoneNumber.length == 10 && hasConsent
}
