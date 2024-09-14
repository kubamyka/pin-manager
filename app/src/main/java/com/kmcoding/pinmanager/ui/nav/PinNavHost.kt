package com.kmcoding.pinmanager.ui.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kmcoding.pinmanager.ui.add.AddNewPinScreen
import com.kmcoding.pinmanager.ui.home.HomeScreen

@Composable
fun PinNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = NavScreen.Home.name,
        modifier = modifier.fillMaxSize(),
    ) {
        composable(route = NavScreen.Home.name) {
            HomeScreen()
        }

        composable(route = NavScreen.AddNewPin.name) {
            AddNewPinScreen()
        }
    }
}
