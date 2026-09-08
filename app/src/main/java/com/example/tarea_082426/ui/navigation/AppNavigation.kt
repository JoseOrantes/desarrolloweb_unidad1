package com.example.tarea_082426.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tarea_082426.ui.Login.LoginScreen
import com.example.tarea_082426.ui.Login.LoginViewModel
import com.example.tarea_082426.ui.profile.ProfileScreen


object AppRoutes { // Define las rutas (URLs internas) de la app
    const val LOGIN = "login"
    const val PROFILE = "profile/{id}/{token}/{nombre}/{apellido}/{usuario}"
    const val REGISTER = "register"

    // Genera la ruta dinamica para perfil con sus argumentos
    fun profileRoute(id: Int, token: String, nombre: String, apellido: String, usuario: String): String {
        return "profile/$id/$token/$nombre/$apellido/$usuario"
    }
}

@Composable
fun AppNavigator( // El "GPS" de la aplicacion
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.LOGIN,
        modifier = modifier
    ) {
        composable(AppRoutes.LOGIN) {
            val viewModel: LoginViewModel = viewModel()
            LoginScreen(
                loginViewModel = viewModel,
                onLoginSuccess = { id, token, nombre, apellido, usuario ->
                    navController.navigate(AppRoutes.profileRoute(id, token, nombre, apellido, usuario)) {
                        popUpTo(AppRoutes.LOGIN) { inclusive = true }
                    }
                }
            )
        }
        composable(AppRoutes.PROFILE) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val apellido = backStackEntry.arguments?.getString("apellido") ?: ""
            val usuario = backStackEntry.arguments?.getString("usuario") ?: ""

            ProfileScreen(
                nombre = nombre,
                apellido = apellido,
                usuario = usuario,
                onLogoutClick = {
                    navController.navigate(AppRoutes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}
