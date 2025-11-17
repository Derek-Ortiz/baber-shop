package ortiz.derek.c4.barber_shop

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ortiz.derek.c4.barber_shop.View.LoginScreen
import ortiz.derek.c4.barber_shop.View.Registrar

@Composable
fun NavManager(){

    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = "Home"
    ){


        composable("Home") {
            LoginScreen(navController)

        }
        composable("Registrar"){
            Registrar(navController)

        }
    }
}