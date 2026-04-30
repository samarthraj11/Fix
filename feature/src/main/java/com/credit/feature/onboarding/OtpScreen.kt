package com.credit.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.credit.designsystem.components.CreditButton
import com.credit.designsystem.tokens.LocalCreditColors
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpScreen(
    onBack: () -> Unit,
    onVerified: () -> Unit,
    viewModel: OtpViewModel = hiltViewModel(),
) {
    val state by viewModel.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val colors = LocalCreditColors.current

    viewModel.collectSideEffect { effect ->
        when (effect) {
            OtpSideEffect.NavigateToHome -> onVerified()
            is OtpSideEffect.ShowError -> snackbarHostState.showSnackbar(effect.message)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = colors.primary,
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colors.surface,
                ),
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            Column(Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
                CreditButton(
                    text = if (state.isVerifying) "Verifying..." else "Verify OTP",
                    onClick = viewModel::onVerify,
                    enabled = state.isComplete && !state.isVerifying,
                )
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Spacer(Modifier.height(8.dp))

            Text(
                text = "Verify Mobile Number",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = colors.onBackground,
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = buildAnnotatedString {
                    append("Enter the 6-digit OTP sent to ")
                    withStyle(SpanStyle(fontWeight = FontWeight.SemiBold, color = colors.onSurface)) {
                        append("+91 ${state.phoneNumber}")
                    }
                },
                style = MaterialTheme.typography.bodyMedium,
                color = colors.onSurfaceVariant,
            )

            Spacer(Modifier.height(32.dp))

            OtpInputRow(
                otp = state.otp,
                onOtpChange = viewModel::onOtpChange,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(24.dp))

            // Resend row
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                if (state.canResend) {
                    TextButton(onClick = viewModel::onResend) {
                        Text(
                            text = "Resend OTP",
                            color = colors.primary,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                } else {
                    val minutes = state.resendSeconds / 60
                    val seconds = state.resendSeconds % 60
                    Text(
                        text = buildAnnotatedString {
                            append("Didn't receive an OTP? ")
                            withStyle(SpanStyle(color = colors.primary, fontWeight = FontWeight.Medium)) {
                                append("Resend in %02d:%02d".format(minutes, seconds))
                            }
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.onSurfaceVariant,
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text = buildAnnotatedString {
                    append("By continuing, I agree to our ")
                    withStyle(SpanStyle(color = colors.primary, fontWeight = FontWeight.Medium)) {
                        append("Terms & Conditions")
                    }
                },
                style = MaterialTheme.typography.bodySmall,
                color = colors.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun OtpInputRow(
    otp: String,
    onOtpChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = LocalCreditColors.current
    val focusRequesters = remember { List(6) { FocusRequester() } }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        repeat(6) { index ->
            val displayChar = if (index < otp.length) otp[index].toString() else ""

            BasicTextField(
                value = displayChar,
                onValueChange = { input ->
                    val newDigit = input.filter { it.isDigit() }.takeLast(1)
                    val newOtp = if (newDigit.isNotEmpty()) {
                        buildString {
                            append(otp)
                            if (index < otp.length) {
                                replace(index, index + 1, newDigit)
                            } else if (index == otp.length) {
                                append(newDigit)
                            }
                        }
                    } else {
                        if (index < otp.length) otp.removeRange(index, index + 1) else otp
                    }
                    onOtpChange(newOtp)
                    if (newDigit.isNotEmpty() && index < 5) {
                        focusRequesters[index + 1].requestFocus()
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .focusRequester(focusRequesters[index])
                    .onKeyEvent { event ->
                        if (event.type == KeyEventType.KeyDown &&
                            event.key == Key.Backspace &&
                            displayChar.isEmpty() &&
                            index > 0
                        ) {
                            onOtpChange(if (otp.isNotEmpty()) otp.dropLast(1) else otp)
                            focusRequesters[index - 1].requestFocus()
                            true
                        } else false
                    },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = if (index < 5) ImeAction.Next else ImeAction.Done,
                ),
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.onSurface,
                ),
                cursorBrush = SolidColor(colors.primary),
                decorationBox = { innerTextField ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .border(
                                width = if (displayChar.isNotEmpty()) 1.5.dp else 1.dp,
                                color = if (displayChar.isNotEmpty()) colors.primary else colors.outlineVariant,
                                shape = RoundedCornerShape(8.dp),
                            )
                            .background(colors.surfaceContainerLow, RoundedCornerShape(8.dp)),
                    ) {
                        innerTextField()
                    }
                },
            )
        }
    }
}
