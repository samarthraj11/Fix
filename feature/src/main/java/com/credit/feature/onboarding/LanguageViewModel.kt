package com.credit.feature.onboarding

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor() : ViewModel(), ContainerHost<LanguageState, LanguageSideEffect> {

    override val container = container<LanguageState, LanguageSideEffect>(LanguageState())

    fun onLanguageSelected(language: Language) = intent {
        reduce { state.copy(selectedLanguage = language) }
    }

    fun onContinue() = intent {
        postSideEffect(LanguageSideEffect.NavigateToPhoneNumber)
    }
}
