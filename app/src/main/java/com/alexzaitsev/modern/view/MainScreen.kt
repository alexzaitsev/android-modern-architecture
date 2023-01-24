package com.alexzaitsev.modern.view

import androidx.compose.runtime.Composable
import com.alexzaitsev.modern.view.compose.theme.MyTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() = MyTheme {
    val viewModel = koinViewModel<MainViewModel>()

    viewModel.getData()
}
