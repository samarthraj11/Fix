package com.credit.feature.onboarding

import androidx.lifecycle.ViewModel
import com.credit.domain.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel(), ContainerHost<OnboardingState, OnboardingSideEffect> {

    override val container = container<OnboardingState, OnboardingSideEffect>(OnboardingState())

    fun onPhoneChange(value: String) = intent {
        reduce { state.copy(phoneNumber = value.filter { it.isDigit() }, error = null) }
    }

    fun onSubmit() = intent {
        if (!state.isValid) {
            postSideEffect(OnboardingSideEffect.ShowError("Enter a valid phone number"))
            return@intent
        }
        reduce { state.copy(isSubmitting = true) }
        sessionManager.login(state.phoneNumber)
        reduce { state.copy(isSubmitting = false) }
        postSideEffect(OnboardingSideEffect.NavigateToHome)
    }
}
