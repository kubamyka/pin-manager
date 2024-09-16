package com.kmcoding.pinmanager.ui.nav

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kmcoding.pinmanager.ui.screens.add.AddNewPinScreen
import com.kmcoding.pinmanager.ui.screens.home.HomeScreen

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

        composable(
            route = NavScreen.AddNewPin.name,
            enterTransition = {
                fadeIn(
                    animationSpec =
                        tween(
                            300,
                            easing = LinearEasing,
                        ),
                ) +
                    slideIntoContainer(
                        animationSpec = tween(300, easing = EaseIn),
                        towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    )
            },
            exitTransition = {
                fadeOut(
                    animationSpec =
                        tween(
                            300,
                            easing = LinearEasing,
                        ),
                ) +
                    slideOutOfContainer(
                        animationSpec = tween(300, easing = EaseOut),
                        towards = AnimatedContentTransitionScope.SlideDirection.End,
                    )
            },
        ) {
            AddNewPinScreen(navigateBack = { navController.navigateUp() })
        }
    }
}
