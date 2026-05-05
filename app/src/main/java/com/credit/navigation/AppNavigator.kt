package com.credit.navigation

import androidx.navigation.NavController
import com.credit.feature.navigation.Navigator
import com.credit.feature.navigation.ScreenDestination

class AppNavigator(private val navController: NavController) : Navigator {

    override fun navigate(destination: ScreenDestination) {
        when (destination) {
            ScreenDestination.Language -> navController.navigate(OnboardingNavGraph.route) {
                popUpTo(HomeNavGraph.route) { inclusive = true }
            }
            ScreenDestination.PhoneNumber -> navController.navigate(Destination.PhoneNumber.route) {
                popUpTo(Destination.Language.route) { inclusive = true }
            }
            is ScreenDestination.Otp -> navController.navigate(Destination.Otp.route(destination.phoneNumber))
            ScreenDestination.Home -> navController.navigate(HomeNavGraph.route) {
                popUpTo(OnboardingNavGraph.route) { inclusive = true }
            }
            ScreenDestination.Back -> navController.popBackStack()
        }
    }
}
