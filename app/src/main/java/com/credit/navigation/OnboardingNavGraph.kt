package com.credit.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.credit.feature.onboarding.language.LanguageScreen
import com.credit.feature.onboarding.phone_otp.OtpScreen
import com.credit.feature.onboarding.phone_number.PhoneNumberScreen

object OnboardingNavGraph : NavGraph {
    override val route = "onboarding"
    override val startRoute = Destination.Language.route

    override fun NavGraphBuilder.destinations() {
        composable(Destination.Language.route) { LanguageScreen() }
        composable(Destination.PhoneNumber.route) { PhoneNumberScreen() }
        composable(
            route = Destination.Otp.route,
            arguments = listOf(navArgument(Destination.Otp.ARG_PHONE) { type = NavType.StringType }),
        ) { OtpScreen() }
    }
}
