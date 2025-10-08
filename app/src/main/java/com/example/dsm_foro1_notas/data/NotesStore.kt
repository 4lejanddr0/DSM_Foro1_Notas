package com.example.dsm_foro1_notas.data

import com.example.dsm_foro1_notas.viewmodel.GradesUiState

/**
 * Almacén en memoria durante la ejecución de la app para guardar
 * y recuperar las notas por usuario (clave = email).
 */
object NotesStore {
    private val notesByEmail = mutableMapOf<String, GradesUiState>()

    /** Guarda el estado de notas para el email dado. */
    fun save(email: String, state: GradesUiState) {
        if (email.isNotBlank()) {
            notesByEmail[email] = state
        }
    }

    /** Carga el estado de notas para el email dado (o null si no hay). */
    fun load(email: String): GradesUiState? {
        if (email.isBlank()) return null
        return notesByEmail[email]
    }

    /** Borra las notas guardadas de un email en particular. */
    fun clear(email: String) {
        if (email.isNotBlank()) {
            notesByEmail.remove(email)
        }
    }

    /** Borra todo (todas las notas de todos los usuarios). */
    fun clearAll() {
        notesByEmail.clear()
    }
}
