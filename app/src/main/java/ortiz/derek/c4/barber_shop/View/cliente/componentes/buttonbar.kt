package ortiz.derek.c4.barber_shop.View.cliente.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ortiz.derek.c4.barber_shop.ui.theme.CircleColor
import ortiz.derek.c4.barber_shop.ui.theme.PrimaryBlue


@Composable
fun ButtonBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(CircleColor)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BottomBarButton(icon = Icons.Default.Home, "Inicio")
        BottomBarButton(icon = Icons.Default.Person, "Perfil")
        BottomBarButton(icon = Icons.Default.Settings, "Ajustes")
    }
}


@Composable
fun BottomBarButton(icon: ImageVector, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        androidx.compose.material3.Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        androidx.compose.material3.Text(
            text = label,
            color = Color.White,
            fontSize = 12.sp
        )
    }
}