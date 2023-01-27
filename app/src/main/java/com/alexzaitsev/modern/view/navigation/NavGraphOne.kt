package com.alexzaitsev.modern.view.navigation

import androidx.compose.runtime.Composable
import com.alexzaitsev.modern.view.navigation.destinations.NavGraph1Name2ScreenDestination
import com.alexzaitsev.modern.view.screen.name1.Name1Screen
import com.alexzaitsev.modern.view.screen.name2.Name2Screen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph
@NavGraph
annotation class NavGraphOne(
    val start: Boolean = false
)

@NavGraphOne(start = true)
@Destination
@Composable
fun NavGraph1Name1Screen(navigator: DestinationsNavigator) = Name1Screen(
    onBackClicked = {},
    goNext = { navigator.navigateTo(NavGraph1Name2ScreenDestination()) },
    showError = { // You can show alert here etc.
        // Compose Destinations have special destinations for that:
        // @Destination(style = DestinationStyle.BottomSheet::class) or
        // @Destination(style = DestinationStyle.Dialog::class)
        // more here https://www.composables.co/lesson/compose-destinations-dialog
    }
)

@NavGraphOne
@Destination
@Composable
fun NavGraph1Name2Screen(navigator: DestinationsNavigator) = Name2Screen(
    showNextButton = false,
    onNextClicked = {}
)
