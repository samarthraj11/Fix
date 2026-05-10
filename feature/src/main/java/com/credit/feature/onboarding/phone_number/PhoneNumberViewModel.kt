package com.credit.feature.onboarding.phone_number

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class PhoneNumberViewModel @Inject constructor() : ViewModel(), ContainerHost<PhoneNumberState, PhoneNumberSideEffect> {

    override val container = container<PhoneNumberState, PhoneNumberSideEffect>(PhoneNumberState())

    fun onPhoneChange(value: String) = intent {
        reduce { state.copy(phoneNumber = value.filter { it.isDigit() }.take(10)) }
    }

    fun onConsentChange(checked: Boolean) = intent {
        reduce { state.copy(hasConsent = checked) }
    }

    fun onSubmit() = intent {
        if (!state.isValid) {
            postSideEffect(PhoneNumberSideEffect.ShowError("Enter a valid 10-digit number and accept terms"))
            return@intent
        }
        reduce { state.copy(isSubmitting = true) }
        postSideEffect(PhoneNumberSideEffect.NavigateToOtp(state.phoneNumber))
        reduce { state.copy(isSubmitting = false) }
    }
}
