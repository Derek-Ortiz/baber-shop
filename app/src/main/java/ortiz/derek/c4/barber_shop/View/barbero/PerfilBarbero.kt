package ortiz.derek.c4.barber_shop.View.barbero

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ortiz.derek.c4.barber_shop.Routes
import ortiz.derek.c4.barber_shop.View.barbero.componentes.ButtonBar
import ortiz.derek.c4.barber_shop.View.barbero.componentes.TopBar
import ortiz.derek.c4.barber_shop.ui.theme.PrimaryBlue
import ortiz.derek.c4.barber_shop.ui.theme.WhiteBackground

@Composable
fun PerfilBarbero(navController: NavController) {
    Scaffold(
        topBar = { TopBar(navController, "Mi perfil") },
        bottomBar = { ButtonBar(navController) }
    ) {
      innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(WhiteBackground)
        ) {
            ProfileHeader()
            ProfileBody(navController)
        }
    }
}

@Composable
fun ProfileHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(PrimaryBlue)
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Profile Icon",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.White),
            tint = PrimaryBlue
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "José Juan Ramos Cabrera",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ProfileBody(navController: NavController) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Información Personal", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Card(elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), shape = RoundedCornerShape(8.dp)) {
            Column {
                InfoRow(icon = Icons.Default.Email, title = "Email", subtitle = "juan_ramos@gmail.com")
                Divider()
                InfoRow(icon = Icons.Default.Phone, title = "Teléfono", subtitle = "999-654-321")
                Divider()
                InfoRow(icon = Icons.Default.LocationOn, title = "Barberia Ubicación", subtitle = "Calle 40 #456, Centro")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Configuración", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        SettingsItem(icon = Icons.Default.Edit, text = "Editar Perfil", onClick = { /* Navigate to Edit Profile */ })
        Spacer(modifier = Modifier.height(8.dp))
        SettingsItem(icon = Icons.Default.ExitToApp, text = "Cerrar Sesión", color = Color.Red, onClick = { navController.navigate(Routes.Login.route) { popUpTo(Routes.Login.route) { inclusive = true } } })
    }
}

@Composable
fun InfoRow(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, subtitle: String) {
    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = title, tint = PrimaryBlue)
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(title, fontWeight = FontWeight.Bold)
            Text(subtitle, color = Color.Gray)
        }
    }
}

@Composable
fun SettingsItem(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String, color: Color = Color.White, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(color),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = text, tint = PrimaryBlue)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text, modifier = Modifier.weight(1f), fontWeight = FontWeight.SemiBold)
            Icon(Icons.Default.ArrowForwardIos, contentDescription = "Arrow")
        }
    }
}
