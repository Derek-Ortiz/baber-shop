package ortiz.derek.c4.barber_shop

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ortiz.derek.c4.barber_shop.View.LoginScreen
import ortiz.derek.c4.barber_shop.View.barbero.BarberiaHome
import ortiz.derek.c4.barber_shop.View.barbero.HistorialCitasBarbero
import ortiz.derek.c4.barber_shop.View.barbero.HomeBarbero
import ortiz.derek.c4.barber_shop.View.barbero.PerfilBarbero
import ortiz.derek.c4.barber_shop.View.barbero.RegistroBarbero
import ortiz.derek.c4.barber_shop.View.barbero.ServicioBarbero
import ortiz.derek.c4.barber_shop.ui.cliente.FechaHoraView
import ortiz.derek.c4.barber_shop.ui.cliente.HomeView
import ortiz.derek.c4.barber_shop.ui.cliente.MisCitasView
import ortiz.derek.c4.barber_shop.ui.cliente.PerfilView
import ortiz.derek.c4.barber_shop.ui.cliente.RegistrarView
import ortiz.derek.c4.barber_shop.ui.cliente.ServiciosView

@Composable
fun NavManager(){

    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = Routes.Login.route
    ){

        composable(Routes.Login.route) {
            LoginScreen(navController)
        }

        composable(Routes.Register.route){
            RegistrarView(navController)
        }

        // RUTAS DEL BARBERO
        composable(Routes.BarberHome.route){
            BarberiaHome(navController)
        }
        composable(Routes.BarberService.route) {
            ServicioBarbero(navController)
        }
        composable(Routes.BarberRegister.route) {
            RegistroBarbero(navController)
        }
        composable(Routes.BarberProfile.route) {
            PerfilBarbero(navController)
        }
        composable(Routes.BarberShopHome.route) {
            HomeBarbero(navController)
        }
        composable(Routes.BarberAppointmentsHistory.route) {
            HistorialCitasBarbero(navController)
        }

        // RUTAS DEL CLIENTE
        composable(Routes.Home.route) {
            HomeView(navController)
        }
        composable(
            route = Routes.Services.route,
            arguments = listOf(navArgument("barberiaId") { type = NavType.IntType })
        ) { backStackEntry ->
            val barberiaId = backStackEntry.arguments?.getInt("barberiaId") ?: 0
            ServiciosView(navController = navController, barberiaId = barberiaId)
        }
        composable(Routes.MyAppointments.route) {
            MisCitasView(navController)
        }
        composable(Routes.Profile.route) {
            PerfilView(navController)
        }
        composable(
            route = Routes.DateTime.route,
            arguments = listOf(navArgument("servicioId") { type = NavType.IntType })
        ) { backStackEntry ->
            val servicioId = backStackEntry.arguments?.getInt("servicioId") ?: 0
            FechaHoraView(navController = navController, servicioId = servicioId)
        }
    }
}