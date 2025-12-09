package ortiz.derek.c4.barber_shop.View.barbero

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ortiz.derek.c4.barber_shop.Models.Servicio
import ortiz.derek.c4.barber_shop.View.barbero.componentes.ButtonBar
import ortiz.derek.c4.barber_shop.View.barbero.componentes.CardServicio
import ortiz.derek.c4.barber_shop.View.barbero.componentes.ServicioDialog
import ortiz.derek.c4.barber_shop.View.barbero.componentes.TopBar
import ortiz.derek.c4.barber_shop.ui.theme.PrimaryBlue
import ortiz.derek.c4.barber_shop.ui.theme.WhiteBackground

@Composable
fun ServicioBarbero(navController: NavController){
    var showDialog by remember { mutableStateOf(false) }
    var servicioToEdit by remember { mutableStateOf<Servicio?>(null) }
    val services = remember { mutableStateOf(getInitialServices()) }

    Scaffold(
        topBar = { TopBar(navController,"Servicios" ) },
        bottomBar = { ButtonBar(navController) },
        contentColor = WhiteBackground,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    servicioToEdit = null
                    showDialog = true
                },
                containerColor = PrimaryBlue,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Añadir servicio")
            }
        }
    ){
      innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            item { Text("Barber Shop",fontSize = 28.sp,
                fontWeight = FontWeight.Bold, color = Color.Black) }
            items(services.value, key = { it.id }) {
                servicio ->
                CardServicio(servicio = servicio, onDelete = {
                    services.value = services.value.filter { it.id != servicio.id }
                }) {
                    servicioToEdit = servicio
                    showDialog = true
                }
            }
        }

        ServicioDialog(
            showDialog = showDialog,
            onDismiss = { showDialog = false },
            onConfirm = {
                newServicio ->
                if (servicioToEdit == null) {
                    services.value = services.value + newServicio.copy(id = (services.value.maxOfOrNull { it.id } ?: 0) + 1)
                } else {
                    services.value = services.value.map { if (it.id == newServicio.id) newServicio else it }
                }
                showDialog = false
            },
            servicioToEdit = servicioToEdit
        )
    }
}

fun getInitialServices(): List<Servicio> {
    return listOf(
        Servicio(1, "Corte de cabello", 80, 30),
        Servicio(2, "Arreglo de barba y bigote", 70, 25),
        Servicio(3, "Afeitado clásico", 60, 20),
        Servicio(4, "Corte + Barba", 140, 45)
    )
}
