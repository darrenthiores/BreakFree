package com.binus.breakfree

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.binus.boarding.BoardingScreen
import com.binus.boarding.BoardingViewModel
import com.binus.breakfree.ui.SplashScreen
import com.binus.breakfree.utils.AppState
import com.binus.breakfree.utils.rememberAppState
import com.binus.core_ui.navigation.Route
import com.binus.core_ui.navigation.TopLevelDestination
import com.binus.core_ui.utils.UiEvent
import com.binus.get_started.GetStartedScreen
import com.binus.login.LoginScreen
import com.binus.login.LoginViewModel

@Composable
fun BreakFree(
    appState: AppState = rememberAppState(),
    shouldShowOnBoarding: Boolean = true,
    shouldShowLogin: Boolean = true,
) {
    val scaffoldState = appState.scaffoldState
    val navController = appState.navController

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            if (appState.shouldShowBottomBar) {

            }
        },
        scaffoldState = scaffoldState
    ) { paddingValues ->
        NavHost(
            modifier = Modifier
                .padding(paddingValues),
            navController = navController,
            startDestination = Route.Splash.name
        ) {
            composable(Route.Splash.name) {
                SplashScreen {
                    if (shouldShowOnBoarding) {
                        navController.navigate(Route.Boarding.name)
                    } else if (shouldShowLogin) {
                        navController.navigate(Route.GetStarted.name)
                    } else {
                        navController.navigate(TopLevelDestination.Home.name)
                    }
                }
            }

            composable(Route.Boarding.name) {
                val viewModel: BoardingViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UiEvent.Success -> {
                                navController.navigate(Route.GetStarted.name)
                            }
                            else -> Unit
                        }
                    }
                }

                BoardingScreen(
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }

            composable(Route.GetStarted.name) {
                GetStartedScreen(
                    onRegisterClick = {
                        navController.navigate(Route.Register.name)
                    },
                    onLoginClick = {
                        navController.navigate(Route.Login.name)
                    }
                )
            }

            composable(Route.Login.name) {
                val viewModel: LoginViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                val number = viewModel.number
                val password = viewModel.password

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UiEvent.Success -> {
                                navController.navigate(TopLevelDestination.Home.name) {
                                    popUpTo(Route.Splash.name)
                                }
                            }
                            else -> Unit
                        }
                    }
                }

                LoginScreen(
                    state = state,
                    number = number,
                    password = password,
                    onEvent = viewModel::onEvent,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(Route.Register.name) {
                Text("Register")
            }

            composable(TopLevelDestination.Home.name) {
                Text("Home")
            }
        }
    }
}