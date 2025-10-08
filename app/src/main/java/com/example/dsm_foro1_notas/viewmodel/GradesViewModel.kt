package com.example.dsm_foro1_notas.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.dsm_foro1_notas.data.NotesStore
import java.util.Locale

data class GradesUiState(
    val n1: String = "",
    val n2: String = "",
    val n3: String = "",
    val error: String? = null
)

class GradesViewModel : ViewModel() {
    var ui by mutableStateOf(GradesUiState())
        private set

    fun onN1(v: String) { ui = ui.copy(n1 = v, error = null) }
    fun onN2(v: String) { ui = ui.copy(n2 = v, error = null) }
    fun onN3(v: String) { ui = ui.copy(n3 = v, error = null) }

    private fun parseOrNull(s: String) = s.replace(",", ".").toFloatOrNull()

    fun calculate(): Result<Pair<Float, Boolean>> {
        val a = parseOrNull(ui.n1)
        val b = parseOrNull(ui.n2)
        val c = parseOrNull(ui.n3)
        if (a == null || b == null || c == null) {
            ui = ui.copy(error = "Ingresa 3 números válidos (usa punto o coma).")
            return Result.failure(IllegalArgumentException("valores inválidos"))
        }
        val avg = (a + b + c) / 3f
        val passed = avg >= 6f
        return Result.success(avg to passed)
    }

    /** Limpia manualmente las notas. */
    fun reset() { ui = GradesUiState() }

    // ===== Persistencia en memoria por usuario (email) =====

    /** Guarda el estado actual de notas para el email dado. */
    fun saveFor(email: String) {
        if (email.isNotBlank()) {
            NotesStore.save(email, ui)
        }
    }

    /** Carga el estado guardado para el email dado. */
    fun loadFor(email: String) {
        ui = if (email.isNotBlank()) {
            NotesStore.load(email) ?: GradesUiState()
        } else {
            GradesUiState()
        }
    }

    companion object {
        fun formatAverage(avg: Float): String =
            String.format(Locale.US, "%.2f", avg)
    }
}
