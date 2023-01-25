package com.alexzaitsev.modern.view.screen.name2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Name2Screen(
    showNextButton: Boolean,
    onNextClicked: () -> Unit
) = Column(modifier = Modifier.padding(16.dp)) {
    Text(text = "Screen 2")

    if (showNextButton) {
        Button(onClick = onNextClicked) { Text(text = "Go to next") }
    }
}
