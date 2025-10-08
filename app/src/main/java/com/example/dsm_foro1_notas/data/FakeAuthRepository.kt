package com.example.dsm_foro1_notas.data

class FakeAuthRepository {
    private val users = mutableMapOf<String, String>()

    fun register(email: String, password: String): Result<Unit> {
        if (users.containsKey(email)) return Result.failure(IllegalStateException("El usuario ya existe"))
        users[email] = password
        return Result.success(Unit)
    }

    fun login(email: String, password: String): Result<Unit> {
        val saved = users[email] ?: return Result.failure(IllegalArgumentException("Usuario no registrado"))
        return if (saved == password) Result.success(Unit)
        else Result.failure(IllegalArgumentException("Contraseña incorrecta"))
    }
}