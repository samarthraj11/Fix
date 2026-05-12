package com.credit.feature.onboarding.phone_number

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.credit.designsystem.tokens.LocalCreditColors
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.OtpScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination<RootGraph>
@Composable
fun PhoneNumberScreen(navigator: DestinationsNavigator, viewModel: PhoneNumberViewModel = hiltViewModel()) {
    val state by viewModel.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val colors = LocalCreditColors.current

    viewModel.collectSideEffect { effect ->
        when (effect) {
            is PhoneNumberSideEffect.NavigateToOtp -> navigator.navigate(OtpScreenDestination(phoneNumber = effect.phoneNumber))
            is PhoneNumberSideEffect.ShowError -> snackBarHostState.showSnackbar(effect.message)
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(snackBarHostState) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState()),
        ) {
            AsyncImage(
                model = "https://lh3.googleusercontent.com/aida-public/AB6AXuBRnBobB97uwmrPZZ324aoNm2YIYsgvvaQsqdVS6onL_S71vx9PYm15s7jwKs8BhO4s4Rbj6eOvbBb8Pb8TNffHUVcL4EPdEJtZ7rKUqPtDdUeJkWukmb9aSoukS82Qe9_aff1_KuQQtkZA-3znFT7f08v05TVqQUIM8UGvXxLoIrjnmRq4CWnfJYUEZd6n4DBqR85iyuqXaT-g141PFhLDO96X32pqhdGveuHKBUJeCUxDyKWULB-Zo_t_2HSloxDzuvWLEmWy4ean",
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = colors.primary, fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)) {
                        append("Join Now")
                    }
                    withStyle(SpanStyle(color = colors.onSurface, fontWeight = FontWeight.Bold, fontSize = 22.sp)) {
                        append(" to Increase your\nCredit Score to ")
                    }
                    withStyle(SpanStyle(color = colors.primary, fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)) {
                        append("750+")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
            ) {
                Text(
                    text = "Enter your Mobile Number",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = colors.onSurface,
                )

                Spacer(Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF2F2F2), RoundedCornerShape(12.dp))
                        .padding(horizontal = 20.dp, vertical = 18.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "+91",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                        color = colors.onSurface,
                    )
                    Spacer(Modifier.width(16.dp))
                    BasicTextField(
                        value = state.phoneNumber,
                        onValueChange = viewModel::onPhoneChange,
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                            color = colors.onSurface,
                            fontWeight = FontWeight.Normal,
                        ),
                        cursorBrush = SolidColor(colors.primary),
                        decorationBox = { innerTextField ->
                            if (state.phoneNumber.isEmpty()) {
                                Text(
                                    text = "98XXX XXXXX",
                                    style = TextStyle(fontSize = 18.sp, color = colors.onSurfaceVariant),
                                )
                            }
                            innerTextField()
                        },
                    )
                }

                Spacer(Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Checkbox(
                        checked = state.hasConsent,
                        onCheckedChange = viewModel::onConsentChange,
                        colors = CheckboxDefaults.colors(
                            checkedColor = colors.primary,
                            uncheckedColor = colors.outlineVariant,
                        ),
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = buildAnnotatedString {
                            append("I agree to the ")
                            withStyle(SpanStyle(color = colors.primary, fontWeight = FontWeight.Medium)) {
                                append("Terms & Privacy Policy")
                            }
                            append(" and authorize credit report fetch.")
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.onSurfaceVariant,
                        modifier = Modifier.padding(top = 12.dp),
                    )
                }

                Spacer(Modifier.height(20.dp))

                Button(
                    onClick = viewModel::onSubmit,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = state.isValid && !state.isSubmitting,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.primary,
                        contentColor = colors.onPrimary,
                        disabledContainerColor = colors.onSurface.copy(alpha = 0.12f),
                        disabledContentColor = colors.onSurface.copy(alpha = 0.38f),
                    ),
                ) {
                    Text(
                        text = if (state.isSubmitting) "Please wait..." else "Next",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                    )
                    if (!state.isSubmitting) {
                        Spacer(Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                }
            }

            Spacer(Modifier.height(32.dp))

            // Footer
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "© 2024 CreditSaathi. RBI Compliant.",
                    style = MaterialTheme.typography.labelSmall,
                    color = colors.onSurfaceVariant,
                )
                Spacer(Modifier.height(8.dp))
                Row {
                    Text(
                        text = "Privacy Policy",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
                        color = colors.primary,
                    )
                    Spacer(Modifier.width(32.dp))
                    Text(
                        text = "Terms of Service",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
                        color = colors.primary,
                    )
                }
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}
