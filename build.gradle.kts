// Este archivo define los plugins disponibles para los módulos del proyecto.
// Se usan alias definidos en el archivo libs.versions.toml para mantener centralizado el control de versiones.

plugins {
    // Plugin para aplicaciones Android. Se declara pero no se aplica aquí.
    alias(libs.plugins.android.application) apply false

    // Plugin de Kotlin para Android. Permite usar características del lenguaje Kotlin en módulos Android.
    alias(libs.plugins.kotlin.android) apply false

    // Plugin para Jetpack Compose. Habilita el uso de UI declarativa en Kotlin.
    alias(libs.plugins.kotlin.compose) apply false
}

