package com.credit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.credit.domain.session.SessionManager
import com.credit.feature.home.HomeScreen
import com.credit.feature.onboarding.LanguageScreen
import com.credit.feature.onboarding.OnboardingScreen

@Composable
fun CreditNavHost(sessionManager: SessionManager) {
    val navController = rememberNavController()
    val startDestination = if (sessionManager.isLoggedIn()) Destination.Home.route else Destination.Language.route

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Destination.Language.route) {
            LanguageScreen(
                onContinue = {
                    navController.navigate(Destination.Onboarding.route) {
                        popUpTo(Destination.Language.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Destination.Onboarding.route) {
            OnboardingScreen(
                onLoggedIn = {
                    navController.navigate(Destination.Home.route) {
                        popUpTo(Destination.Onboarding.route) { inclusive = true }
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
