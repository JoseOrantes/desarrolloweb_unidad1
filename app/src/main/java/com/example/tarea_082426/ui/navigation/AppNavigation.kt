package com.example.tarea_082426.ui.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tarea_082426.ui.Login.LoginScreen
import com.example.tarea_082426.ui.Login.LoginViewModel
import com.example.tarea_082426.ui.profile.ProfileScreen
import com.example.tarea_082426.ui.register.RegisterScreen
import com.example.tarea_082426.ui.register.RegisterViewModel


object AppRoutes { // Define las rutas (URLs internas) de la app
    const val LOGIN = "login"
    const val PROFILE = "profile/{id}/{token}/{nombre}/{apellido}/{usuario}"
    const val REGISTER = "register"

    // Genera la ruta dinamica para perfil con sus argumentos
    fun profileRoute(id: Int, token: String, nombre: String, apellido: String, usuario: String): String {
        val encToken = Uri.encode(token)
        val encNombre = Uri.encode(nombre)
        val encApellido = Uri.encode(apellido)
        val encUsuario = Uri.encode(usuario)
        return "profile/$id/$encToken/$encNombre/$encApellido/$encUsuario"
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
                },
                modifier = Modifier,
                onRegisterClick = {
                    navController.navigate(AppRoutes.REGISTER)
                }
            )
        }
        composable(AppRoutes.REGISTER) {
            val viewModel: RegisterViewModel = viewModel()
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(AppRoutes.LOGIN) {
                        popUpTo(AppRoutes.REGISTER) { inclusive = true }
                    }
                },
                onBackToLogin = {
                    navController.popBackStack()
                },
                registerViewModel = viewModel
            )
        }
        composable(
            route = AppRoutes.PROFILE,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("token") { type = NavType.StringType },
                navArgument("nombre") { type = NavType.StringType },
                navArgument("apellido") { type = NavType.StringType },
                navArgument("usuario") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val apellido = backStackEntry.arguments?.getString("apellido") ?: ""
            val usuario = backStackEntry.arguments?.getString("usuario") ?: ""

            ProfileScreen(
                id = id,
                nombre = nombre,
                apellido = apellido,
                usuario = usuario,
                onLogoutClick = {
                    navController.navigate(AppRoutes.LOGIN) {
                        popUpTo(AppRoutes.LOGIN) { inclusive = true }
                    }
                }
            )
        }
    }
}
