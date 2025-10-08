package com.example.dsm_foro1_notas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun FancyBackground(modifier: Modifier = Modifier) {
    val gradient = Brush.radialGradient(
        colors = listOf(Color(0xFF4CAF50), Color(0xFF1B5E20)),
        center = Offset(300f, 300f),
        radius = 800f
    )
    Box(Modifier.fillMaxSize().background(gradient).then(modifier))
}
