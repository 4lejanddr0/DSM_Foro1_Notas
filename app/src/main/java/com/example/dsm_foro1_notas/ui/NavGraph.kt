package com.example.dsm_foro1_notas.ui

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dsm_foro1_notas.ui.screens.AuthScreen
import com.example.dsm_foro1_notas.ui.screens.GradesScreen
import com.example.dsm_foro1_notas.ui.screens.ResultScreen
import com.example.dsm_foro1_notas.ui.screens.WelcomeScreen
import com.example.dsm_foro1_notas.viewmodel.AuthViewModel
import com.example.dsm_foro1_notas.viewmodel.GradesViewModel

private fun nameFromEmail(email: String): String {
    val raw = email.substringBefore("@").replace('.', ' ')
    return raw.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}

@Composable
fun AppNav() {
    val navController = rememberNavController()
    val authVm: AuthViewModel = viewModel()
    val gradesVm: GradesViewModel = viewModel()   // VM global (opción rápida)

    NavHost(navController = navController, startDestination = "auth") {

        composable(route = "auth") { backStackEntry ->
            AuthScreen(vm = authVm) { email ->
                val name = nameFromEmail(email)
                navController.navigate("welcome/${Uri.encode(name)}")
            }
        }

        composable(
            route = "welcome/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "Estudiante"
            WelcomeScreen(name = name) {
                navController.navigate("grades/${Uri.encode(name)}")
            }
        }

        composable(
            route = "grades/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "Estudiante"

            // Email del usuario actualmente logueado (lo guarda AuthViewModel)
            val email = authVm.ui.email

            // Cargar notas guardadas de ese email al entrar a la pantalla
            LaunchedEffect(email) {
                if (email.isNotBlank()) gradesVm.loadFor(email)
            }

            GradesScreen(name = name, vm = gradesVm) { avg, passed ->
                // Guardar las notas de este usuario antes de ir al resultado
                if (email.isNotBlank()) gradesVm.saveFor(email)

                val avgFormatted = GradesViewModel.formatAverage(avg)
                navController.navigate(
                    "result/${Uri.encode(name)}/${Uri.encode(avgFormatted)}/$passed"
                )
            }
        }

        composable(
            route = "result/{name}/{avg}/{passed}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("avg") { type = NavType.StringType },
                navArgument("passed") { type = NavType.BoolType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "Estudiante"
            val avg = backStackEntry.arguments?.getString("avg") ?: "0.00"
            val passed = backStackEntry.arguments?.getBoolean("passed") ?: false

            ResultScreen(
                name = name,
                averageFormatted = avg,
                passed = passed,
                onTryAgain = {
                    navController.popBackStack(
                        route = "grades/${Uri.encode(name)}",
                        inclusive = false
                    )
                },
                onLogout = {
                    // NO hacemos gradesVm.reset() para conservar lo guardado por email
                    authVm.logout()
                    navController.navigate("auth") {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
