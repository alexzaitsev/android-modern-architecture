package com.alexzaitsev.modern.view.navigation

import androidx.compose.runtime.Composable
import com.alexzaitsev.modern.view.screen.name1.Name1Screen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun Name1Screen(navigator: DestinationsNavigator) = Name1Screen(
    onBackClicked = {},
    goNext = {  /* navigator.navigateTo(HomeScreenDestination(), popCurrent = true) */ },
    showError = { // You can show alert here etc.
        // Compose Destinations have special destinations for that:
        // @Destination(style = DestinationStyle.BottomSheet::class) or
        // @Destination(style = DestinationStyle.Dialog::class)
        // more here https://www.composables.co/lesson/compose-destinations-dialog
    }
)
