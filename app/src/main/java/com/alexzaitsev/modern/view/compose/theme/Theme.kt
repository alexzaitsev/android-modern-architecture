package com.alexzaitsev.modern.view.compose.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable

@Composable
fun MyTheme(content: @Composable () -> Unit) = MaterialTheme(
    colors = colors,
    typography = typography,
    shapes = shapes,
    content = content
)
