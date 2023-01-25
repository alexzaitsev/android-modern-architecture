package com.alexzaitsev.modern.view.navigation

import androidx.compose.runtime.Composable
import com.alexzaitsev.modern.view.screen.root.RootScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun RootScreen(navigator: DestinationsNavigator) = RootScreen(
    openGraph1 = { navigator.navigateTo(NavGraphs.one) },
    openGraph2 = { navigator.navigateTo(NavGraphs.two) }
)
