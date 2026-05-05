package com.credit.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.credit.feature.home.HomeScreen

object HomeNavGraph : NavGraph {
    override val route = "home_graph"
    override val startRoute = Destination.Home.route

    override fun NavGraphBuilder.destinations() {
        composable(Destination.Home.route) { HomeScreen() }
    }
}
