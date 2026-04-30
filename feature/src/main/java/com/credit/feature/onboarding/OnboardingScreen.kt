package com.credit.feature.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.credit.designsystem.components.CreditButton
import com.credit.designsystem.components.CreditTextField
import com.credit.designsystem.tokens.LocalCreditColors
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun OnboardingScreen(
    onLoggedIn: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    viewModel.collectSideEffect { effect ->
        when (effect) {
            OnboardingSideEffect.NavigateToHome -> onLoggedIn()
            is OnboardingSideEffect.ShowError -> snackbarHostState.showSnackbar(effect.message)
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Text(
                text = "Welcome",
                style = MaterialTheme.typography.headlineMedium,
                color = LocalCreditColors.current.onBackground,
            )
            Text(
                text = "Enter your phone number to continue",
                color = LocalCreditColors.current.onSurfaceVariant,
            )
            CreditTextField(
                value = state.phoneNumber,
                onValueChange = viewModel::onPhoneChange,
                label = "Phone number",
                keyboardType = KeyboardType.Phone
            )
            CreditButton(
                text = if (state.isSubmitting) "Please wait..." else "Continue",
                onClick = { viewModel.onSubmit() },
                enabled = state.isValid && !state.isSubmitting
            )
        }
    }
}
