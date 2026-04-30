package com.credit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.credit.domain.session.SessionManager
import com.credit.feature.home.HomeScreen
import com.credit.feature.onboarding.LanguageScreen
import com.credit.feature.onboarding.OtpScreen
import com.credit.feature.onboarding.PhoneNumberScreen

@Composable
fun CreditNavHost(sessionManager: SessionManager) {
    val navController = rememberNavController()
    val startDestination = if (sessionManager.isLoggedIn()) Destination.Home.route else Destination.Language.route

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Destination.Language.route) {
            LanguageScreen(
                onContinue = {
                    navController.navigate(Destination.PhoneNumber.route) {
                        popUpTo(Destination.Language.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Destination.PhoneNumber.route) {
            PhoneNumberScreen(
                onNavigateToOtp = { phoneNumber ->
                    navController.navigate(Destination.Otp.route(phoneNumber))
                }
            )
        }

        composable(
            route = Destination.Otp.route,
            arguments = listOf(navArgument(Destination.Otp.ARG_PHONE) { type = NavType.StringType }),
        ) {
            OtpScreen(
                onBack = { navController.popBackStack() },
                onVerified = {
                    navController.navigate(Destination.Home.route) {
                        popUpTo(Destination.Language.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Destination.Home.route) {
            HomeScreen(
                onLoggedOut = {
                    navController.navigate(Destination.Language.route) {
                        popUpTo(Destination.Home.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
