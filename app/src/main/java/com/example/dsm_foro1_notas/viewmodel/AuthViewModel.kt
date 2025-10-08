package com.example.dsm_foro1_notas.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.dsm_foro1_notas.data.FakeAuthRepository

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val isLoginMode: Boolean = true,
    val error: String? = null,
    val loading: Boolean = false,
    val success: Boolean = false
)

class AuthViewModel(
    private val repo: FakeAuthRepository = FakeAuthRepository()
) : ViewModel() {

    var ui by mutableStateOf(AuthUiState())
        private set

    fun toggleMode() {
        ui = ui.copy(isLoginMode = !ui.isLoginMode, error = null, success = false)
    }

    fun onEmailChange(v: String) { ui = ui.copy(email = v, error = null, success = false) }
    fun onPasswordChange(v: String) { ui = ui.copy(password = v, error = null, success = false) }

    private fun isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(ui.email).matches()
    private fun isValidPassword() = ui.password.length >= 6
    fun canSubmit() = isValidEmail() && isValidPassword() && !ui.loading

    fun submit() {
        if (!isValidEmail()) { ui = ui.copy(error = "Correo inválido"); return }
        if (!isValidPassword()) { ui = ui.copy(error = "La contraseña debe tener al menos 6 caracteres"); return }
        ui = ui.copy(loading = true, error = null)
        val result = if (ui.isLoginMode) repo.login(ui.email, ui.password) else repo.register(ui.email, ui.password)
        ui = if (result.isSuccess) ui.copy(loading = false, success = true)
        else ui.copy(loading = false, error = result.exceptionOrNull()?.message ?: "Error")
    }

    fun logout() {
        ui = AuthUiState()
    }
}
