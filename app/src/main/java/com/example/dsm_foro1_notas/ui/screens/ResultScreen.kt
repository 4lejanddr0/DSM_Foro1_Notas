@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.dsm_foro1_notas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ResultScreen(
    name: String,
    averageFormatted: String,
    passed: Boolean,
    onTryAgain: () -> Unit,
    onLogout: () -> Unit
) {
    val statusText = if (passed) "APROBÓ 🎉" else "REPROBÓ ❌"
    val statusColor = if (passed) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.error

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Resultado") }) }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Estudiante: $name")
            Spacer(Modifier.height(12.dp))

            Text("Promedio:", fontWeight = FontWeight.SemiBold)
            Text(
                averageFormatted,
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(Modifier.height(12.dp))
            Text("Estado: $statusText", color = statusColor)

            Spacer(Modifier.height(24.dp))
            Button(
                onClick = onTryAgain,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Ingresar otras notas") }

            Spacer(Modifier.height(8.dp))

            OutlinedButton(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Cerrar sesión") }
        }
    }
}
