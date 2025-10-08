package com.example.dsm_foro1_notas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.dsm_foro1_notas.viewmodel.GradesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradesScreen(name: String, vm: GradesViewModel, onAverageReady: (avg: Float, passed: Boolean) -> Unit) {
    val state = vm.ui
    Scaffold(topBar = { CenterAlignedTopAppBar(title = { Text("Ingreso de notas") }) }) { inner ->
        Column(Modifier.padding(inner).fillMaxSize().padding(16.dp)) {
            Text("Estudiante: $name")
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(value = state.n1, onValueChange = vm::onN1, label = { Text("Nota 1") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = state.n2, onValueChange = vm::onN2, label = { Text("Nota 2") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = state.n3, onValueChange = vm::onN3, label = { Text("Nota 3") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                modifier = Modifier.fillMaxWidth())

            if (state.error != null) { Spacer(Modifier.height(8.dp)); Text(state.error ?: "", color = MaterialTheme.colorScheme.error) }

            Spacer(Modifier.height(16.dp))
            Button(onClick = {
                vm.calculate().onSuccess { (avg, passed) -> onAverageReady(avg, passed) }
            }, modifier = Modifier.fillMaxWidth()) { Text("Calcular promedio") }
        }
    }
}
