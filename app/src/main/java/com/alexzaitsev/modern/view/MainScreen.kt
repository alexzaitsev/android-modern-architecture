package com.alexzaitsev.modern.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.alexzaitsev.modern.view.compose.theme.MyTheme
import com.alexzaitsev.modern.view.navigation.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun MainScreen() = MyTheme {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        backgroundColor = Color.Transparent,
        scaffoldState = scaffoldState,
        snackbarHost = { scaffoldState.snackbarHostState }
    ) { padding ->
        ScaffoldBody(padding)
    }
}

@Composable
private fun ScaffoldBody(
    innerPadding: PaddingValues,
) = Box(
    modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
) {
    DestinationsNavHost(
        modifier = Modifier.fillMaxSize(),
        navGraph = NavGraphs.root // if this import is not found run ./gradlew kspDebugKotlin
    )
}
