package com.kmcoding.pinmanager.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kmcoding.pinmanager.R
import com.kmcoding.pinmanager.ui.nav.NavScreen
import com.kmcoding.pinmanager.ui.nav.PinAppBar
import com.kmcoding.pinmanager.ui.nav.PinNavHost

@Composable
fun PinManagerApp(navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = NavScreen.valueOf(backStackEntry?.destination?.route ?: NavScreen.Home.name)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            PinAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
            )
        },
        floatingActionButton = {
            if (currentScreen == NavScreen.Home) {
                FloatingActionButton(onClick = { navController.navigate(NavScreen.AddNewPin.name) }) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription =
                            stringResource(
                                id = R.string.add_new_pin,
                            ),
                    )
                }
            }
        },
    ) { innerPadding ->
        PinNavHost(modifier = Modifier.padding(innerPadding), navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun PinManagerAppPreview() {
    PinManagerApp()
}
