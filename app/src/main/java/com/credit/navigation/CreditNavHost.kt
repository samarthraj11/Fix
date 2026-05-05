package com.credit.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.credit.feature.navigation.LocalNavigator

@Composable
fun CreditNavHost(startGraph: NavGraph, graphs: List<NavGraph>) {
    val navController = rememberNavController()
    val navigator = remember(navController) { AppNavigator(navController) }

    CompositionLocalProvider(LocalNavigator provides navigator) {
        NavHost(navController = navController, startDestination = startGraph.route) {
            graphs.forEach { graph ->
                navigation(startDestination = graph.startRoute, route = graph.route) {
                    with(graph) { destinations() }
                }
            }
        }
    }
}
