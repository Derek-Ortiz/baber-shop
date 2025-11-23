package ortiz.derek.c4.barber_shop.View.barbero

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ortiz.derek.c4.barber_shop.View.barbero.componentes.ButtonBar
import ortiz.derek.c4.barber_shop.View.barbero.componentes.CardDate
import ortiz.derek.c4.barber_shop.View.barbero.componentes.TopBar
import ortiz.derek.c4.barber_shop.ui.theme.WhiteBackground

@Composable
fun ServicioBarbero(navController: NavController){
    Scaffold(
        topBar = { TopBar(navController,"Servicio" ) },
        bottomBar = { ButtonBar(navController) },
        contentColor = WhiteBackground
    ){
      innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {

            CardDate("jose","12:00 A.M - 1:00 P.M")
            CardDate("jose","12:00 A.M - 1:00 P.M")
            CardDate("jose","12:00 A.M - 1:00 P.M")
            CardDate("jose","12:00 A.M - 1:00 P.M")
        }
    }
}