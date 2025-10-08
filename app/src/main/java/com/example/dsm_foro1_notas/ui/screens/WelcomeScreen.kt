@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.dsm_foro1_notas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api

@Composable
fun WelcomeScreen(name: String, onContinue: () -> Unit) {
    Scaffold(topBar = { CenterAlignedTopAppBar(title = { Text("Bienvenido") }) }) { inner ->
        Column(
            Modifier.padding(inner).fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
        ) {
            Text("Hola, $name 👋", textAlign = TextAlign.Center)
            Spacer(Modifier.height(16.dp))
            Button(onClick = onContinue) { Text("Continuar → Ingresar notas") }
        }
    }
}
