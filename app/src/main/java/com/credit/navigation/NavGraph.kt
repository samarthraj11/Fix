package com.credit.navigation

import androidx.navigation.NavGraphBuilder

interface NavGraph {
    val route: String
    val startRoute: String
    fun NavGraphBuilder.destinations()
}
