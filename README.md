📝 DSM Foro 1 – App de Notas (Jetpack Compose)

Aplicación Android desarrollada con Jetpack Compose.
Permite login/registro, ingreso de 3 notas y cálculo del promedio para mostrar si el usuario APROBÓ o REPROBÓ.
Las notas permanecen activas solo mientras la app está abierta.

🚀 Requisitos

- Android Studio (Hedgehog / Koala o superior)
- Android SDK: compileSdk = 36, minSdk = 24
- Kotlin + Jetpack Compose (Material 3)
- Gradle Wrapper incluido (no requiere instalación adicion

⚙️ Ejecución
Android Studio

1- Clonar el repositorio:

git clone https://github.com/4lejanddr0/DSM_Foro1_Notas.git

2- Abrir Android Studio → File → Open → seleccionar la carpeta del proyecto (que contiene settings.gradle.kts).
3- Esperar Gradle Sync (instalar SDK si lo solicita).
4- Crear o seleccionar un emulador (por ejemplo, Pixel 5 – API 34) o conectar un dispositivo físico.
5- Ejecutar con el botón ▶️ Run ‘app’.

🧩 Flujo de uso

Login / Registro
- Correo válido y contraseña ≥ 6 caracteres.
- Posibilidad de alternar entre iniciar sesión o crear cuenta.

Pantalla de bienvenida
- Muestra el nombre derivado del correo (parte antes de “@”, capitalizada).

Ingreso de notas
- 3 campos numéricos (admite decimales con “.”).
- Botón para calcular promedio.

Resultado
- Promedio con 2 decimales.
- Muestra “APROBÓ” (≥ 6.0) o “REPROBÓ”.
- Botones: Ingresar otras notas / Cerrar sesión.

Sesión
- Al cerrar sesión, se limpia el historial del usuario y se regresa al login.

👥 Integrantes

- Jesus Alejandro campos Landaverde CCL212345
- Devin Ivan Mendoza Ortiz MO190319
- Consuelo Astrid Landa Hernandez LH201536
- Arturo Jose Gomez Henriquez GH191748


LINK video de explicacion del proyecto: 
