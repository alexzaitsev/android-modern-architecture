package com.alexzaitsev.modern.view.navigation

import androidx.compose.runtime.Composable
import com.alexzaitsev.modern.view.navigation.destinations.NavGraph2Name3ScreenDestination
import com.alexzaitsev.modern.view.screen.name2.Name2Screen
import com.alexzaitsev.modern.view.screen.name3.Name3Screen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph
@NavGraph
annotation class NavGraphTwo(
    val start: Boolean = false
)

@NavGraphTwo(start = true)
@Destination
@Composable
fun NavGraph2Name2Screen(navigator: DestinationsNavigator) = Name2Screen(
    showNextButton = true,
    onNextClicked = { navigator.navigateTo(NavGraph2Name3ScreenDestination()) }
)

@NavGraphTwo
@Destination
@Composable
fun NavGraph2Name3Screen(navigator: DestinationsNavigator) = Name3Screen()
