package com.example.dsm_foro1_notas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.HowToReg
import androidx.compose.material.icons.outlined.Login
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.dsm_foro1_notas.viewmodel.AuthUiState
import com.example.dsm_foro1_notas.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(vm: AuthViewModel, onSuccess: (email: String) -> Unit) {
    val state: AuthUiState = vm.ui
    val focus = LocalFocusManager.current

    LaunchedEffect(state.success) { if (state.success) onSuccess(state.email) }

    Scaffold(topBar = { CenterAlignedTopAppBar(title = { Text(if (state.isLoginMode) "Iniciar sesión" else "Registro") }) }) { inner ->
        Column(
            Modifier.padding(inner).fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = state.email, onValueChange = vm::onEmailChange, label = { Text("Correo electrónico") },
                leadingIcon = { Icon(Icons.Default.Email, null) }, singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = state.password, onValueChange = vm::onPasswordChange, label = { Text("Contraseña (min 6)") },
                leadingIcon = { Icon(Icons.Default.Lock, null) }, singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { focus.clearFocus() }),
                modifier = Modifier.fillMaxWidth()
            )
            if (state.error != null) { Spacer(Modifier.height(8.dp)); Text(state.error ?: "", color = MaterialTheme.colorScheme.error) }
            Spacer(Modifier.height(20.dp))
            Button(onClick = vm::submit, enabled = vm.canSubmit(), modifier = Modifier.fillMaxWidth()) {
                if (state.isLoginMode) { Icon(Icons.Outlined.Login, null); Spacer(Modifier.width(8.dp)); Text("Iniciar sesión") }
                else { Icon(Icons.Outlined.HowToReg, null); Spacer(Modifier.width(8.dp)); Text("Registrarme") }
            }
            Spacer(Modifier.height(8.dp))
            TextButton(onClick = vm::toggleMode) {
                Text(if (state.isLoginMode) "¿No tienes cuenta? Regístrate" else "¿Ya tienes cuenta? Inicia sesión")
            }
        }
    }
}
