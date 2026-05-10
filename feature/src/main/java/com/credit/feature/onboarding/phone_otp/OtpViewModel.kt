package com.credit.feature.onboarding.phone_otp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.credit.domain.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), ContainerHost<OtpState, OtpSideEffect> {

    private val phoneNumber: String = checkNotNull(savedStateHandle[ARG_PHONE_NUMBER])

    override val container = container<OtpState, OtpSideEffect>(OtpState(phoneNumber = phoneNumber))

    private var timerJob: Job? = null

    init {
        startResendTimer()
    }

    fun onOtpChange(value: String) = intent {
        reduce { state.copy(otp = value.filter { it.isDigit() }.take(6)) }
    }

    fun onVerify() = intent {
        if (!state.isComplete) return@intent
        reduce { state.copy(isVerifying = true) }
        sessionManager.login(state.phoneNumber)
        reduce { state.copy(isVerifying = false) }
        postSideEffect(OtpSideEffect.NavigateToHome)
    }

    fun onResend() = intent {
        if (!state.canResend) return@intent
        reduce { state.copy(resendSeconds = 25, canResend = false) }
        startResendTimer()
    }

    private fun startResendTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            for (remaining in 24 downTo 0) {
                delay(1000)
                intent { reduce { state.copy(resendSeconds = remaining) } }
            }
            intent { reduce { state.copy(canResend = true) } }
        }
    }

    companion object {
        const val ARG_PHONE_NUMBER = "phoneNumber"
    }
}
