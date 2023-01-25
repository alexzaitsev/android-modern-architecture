package com.alexzaitsev.modern.view.screen.name1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.get
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun Name1Screen(
    onBackClicked: () -> Unit,
    goNext: () -> Unit,
    showError: () -> Unit
) {
    val viewModel = get<Name1ViewModel>()

    SideEffect(
        viewModel = viewModel,
        goNext = goNext,
        showError = showError
    )
    ViewState(
        viewModel = viewModel,
        onBackClicked = onBackClicked
    )
}

@Composable
private fun SideEffect(
    viewModel: Name1ViewModel,
    goNext: () -> Unit,
    showError: () -> Unit
) = viewModel.collectSideEffect { effect ->
    return@collectSideEffect when (effect) {
        Name1SideEffect.GoNext -> goNext()
        Name1SideEffect.ShowError -> showError()
    }
}

@Composable
private fun ViewState(
    viewModel: Name1ViewModel,
    onBackClicked: () -> Unit
) = Name1ViewState(
    state = viewModel.collectAsState().value,
    onBackClicked = onBackClicked,
    onLoadDataClicked = { viewModel.sendAction(Name1UserAction.LoadData) },
    onAction2Clicked = { viewModel.sendAction(Name1UserAction.Action2) },
    onAction3Clicked = { viewModel.sendAction(Name1UserAction.Action3) },
    onAction4Clicked = { viewModel.sendAction(Name1UserAction.Action4) }
)

@Composable
private fun Name1ViewState(
    state: Name1ViewState,
    onBackClicked: () -> Unit,
    onLoadDataClicked: () -> Unit,
    onAction2Clicked: () -> Unit,
    onAction3Clicked: () -> Unit,
    onAction4Clicked: () -> Unit
) = Column(modifier = Modifier.padding(16.dp)) {
    Text(modifier = Modifier.padding(bottom = 16.dp), text = state.textToDisplay)
    if (state.loading) {
        CircularProgressIndicator()
    } else {
        Button(onClick = onLoadDataClicked) { Text(text = "Load data") }
    }
    Button(onClick = onAction2Clicked) { Text(text = "Action2") }
    Button(onClick = onAction3Clicked) { Text(text = "Action3") }
    Button(onClick = onAction4Clicked) { Text(text = "Action4") }
}
