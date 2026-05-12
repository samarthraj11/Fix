package com.credit.feature

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.generated.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.generated.destinations.LanguageScreenDestination

@Composable
fun CreditNavHost(isLoggedIn: Boolean) {
    DestinationsNavHost(
        navGraph = NavGraphs.root,
        start = if (isLoggedIn) HomeScreenDestination else LanguageScreenDestination,
    )
}
