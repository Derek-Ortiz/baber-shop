package ortiz.derek.c4.barber_shop.View.barbero.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ortiz.derek.c4.barber_shop.R
import ortiz.derek.c4.barber_shop.ui.theme.CircleColor


@Composable
fun ButtonBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(CircleColor)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BottomBarButton(icon = Icons.Default.Person, "Perfil","PerfilBarbero", navController)
        BottomBarButton(icon = Icons.Default.Home, "Inicio", "HomeBarbero", navController)
        BottomBarButton(icon = Icons.Default.CalendarMonth, "Calendario", "HistorialCitasBarbero", navController)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(onClick = {
                navController.navigate("ServicioBarbero")
            }) {
                Image(
                    painter = painterResource(id = R.drawable.imgtijeas),
                    contentDescription = "Barber icon",
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(text = "Servicios",
                color = Color.White,
                fontSize = 12.sp)

        }

    }
}


@Composable
fun BottomBarButton(icon: ImageVector, label: String, route:String, navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = {
                navController.navigate(route)
            }) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = label,
            color = Color.White,
            fontSize = 12.sp
        )
    }
}