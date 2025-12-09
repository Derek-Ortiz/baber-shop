package ortiz.derek.c4.barber_shop.View.barbero

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ortiz.derek.c4.barber_shop.View.barbero.componentes.ButtonBar
import ortiz.derek.c4.barber_shop.View.barbero.componentes.TopBar
import ortiz.derek.c4.barber_shop.ui.theme.*


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun BarberiaHome(navController: NavController) {

    Scaffold(
        topBar = { TopBar(navController, "Barberias") },
        bottomBar = { ButtonBar(navController) },
        containerColor = WhiteBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            var nombre by remember { mutableStateOf("") }
            var ubicacion by remember { mutableStateOf("") }
            var horarioAtencion by remember { mutableStateOf("") }
            var logoBarberia by remember { mutableStateOf("") }


            // Título Bienvenido
            Text(
                text = "Bienvenido",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp, bottomStart = 15.dp, bottomEnd = 15.dp))
                    .background(White)) {
                // Sección Añade tu barbería
                Text(
                    text = "Añade tu barbería",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryColor
                )

                // Campo Nombre de la barbería
                Text(
                    text = "Nombre de la barberia",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryColor
                )
                TextField(value = nombre, onValueChange = { nombre = it})
                Spacer(modifier = Modifier.height(10.dp))

                // Campo Ubicación de la barbería
                Text(
                    text = "Ubicación de la barberia",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryColor
                )
                TextField(value = ubicacion, onValueChange = { ubicacion = it})
                Spacer(modifier = Modifier.height(10.dp))

                // Campo Horario de atención
                Text(
                    text = "Horario de atención",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryColor
                )
                TextField(value = horarioAtencion, onValueChange = { horarioAtencion = it})
                Spacer(modifier = Modifier.height(10.dp))

                // Campo Logotipo de la barbería
                Text(
                    text = "Logotipo de la barbería",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextColor
                )


                // Selector de imagen
                ImageSelector()

                Spacer(modifier = Modifier.weight(1f))

                // Botón Registrar
                Button(
                    onClick = {
                        navController.navigate("BarberoHome")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SuccessGreen,
                        contentColor = PrimaryColor
                    )
                ) {
                    Text(
                        text = "Registrar",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }

        }
    }
}

@Composable
fun ImageSelector() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(SurfaceColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu_gallery),
                contentDescription = "Añadir imagen",
                tint = HintColor,
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = "Imagen",
                color = HintColor,
                fontSize = 16.sp
            )
        }
    }
}