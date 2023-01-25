package com.alexzaitsev.modern.view.screen.root

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RootScreen(
    openGraph1: () -> Unit,
    openGraph2: () -> Unit
) = Column(modifier = Modifier.padding(16.dp)) {
    Button(onClick = openGraph1) { Text(text = "Open NavGraph1") }
    Button(onClick = openGraph2) { Text(text = "Open NavGraph2") }
}
