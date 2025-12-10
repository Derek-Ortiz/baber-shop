package ortiz.derek.c4.barber_shop.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ortiz.derek.c4.barber_shop.ui.cliente.FechaHoraView
import ortiz.derek.c4.barber_shop.ui.cliente.HomeView
import ortiz.derek.c4.barber_shop.ui.cliente.MisCitasView
import ortiz.derek.c4.barber_shop.ui.cliente.PerfilView
import ortiz.derek.c4.barber_shop.ui.cliente.RegistrarView
import ortiz.derek.c4.barber_shop.ui.cliente.ServiciosView

@Composable
fun NavManager() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            // Redirecting to registrar for now as requested
            RegistrarView(navController)
        }
        composable("registrar") {
            RegistrarView(navController)
        }
        composable("home") {
            HomeView(navController)
        }
        composable(
            route = "servicios/{barberiaId}",
            arguments = listOf(navArgument("barberiaId") { type = NavType.IntType })
        ) { backStackEntry ->
            val barberiaId = backStackEntry.arguments?.getInt("barberiaId") ?: 0
            ServiciosView(navController, barberiaId)
        }
        composable(
            route = "fecha/{servicioId}",
            arguments = listOf(navArgument("servicioId") { type = NavType.IntType })
        ) { backStackEntry ->
            val servicioId = backStackEntry.arguments?.getInt("servicioId") ?: 0
            FechaHoraView(navController, servicioId)
        }
        composable("citas") {
            MisCitasView(navController)
        }
        composable("perfil") {
            PerfilView(navController)
        }
    }
}
