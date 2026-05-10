package com.credit.feature.onboarding.phone_otp

data class OtpState(
    val otp: String = "",
    val phoneNumber: String = "",
    val isVerifying: Boolean = false,
    val resendSeconds: Int = 25,
    val canResend: Boolean = false,
) {
    val isComplete: Boolean get() = otp.length == 6
}
