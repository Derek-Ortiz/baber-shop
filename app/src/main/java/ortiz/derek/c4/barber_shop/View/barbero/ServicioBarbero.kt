package ortiz.derek.c4.barber_shop.View.barbero

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ortiz.derek.c4.barber_shop.data.remote.dto.ServicioDto
import ortiz.derek.c4.barber_shop.View.barbero.componentes.ButtonBar
import ortiz.derek.c4.barber_shop.View.barbero.componentes.CardServicio
import ortiz.derek.c4.barber_shop.View.barbero.componentes.ServicioDialog
import ortiz.derek.c4.barber_shop.View.barbero.componentes.TopBar
import ortiz.derek.c4.barber_shop.ui.theme.PrimaryBlue
import ortiz.derek.c4.barber_shop.ui.theme.WhiteBackground

@Composable
fun ServicioBarbero(navController: NavController, viewModel: ServicioBarberoViewModel = hiltViewModel()) {
    var showDialog by remember { mutableStateOf(false) }
    var servicioToEdit by remember { mutableStateOf<ServicioDto?>(null) }
    val state by viewModel.state

    Scaffold(
        topBar = { TopBar(navController, "Servicios") },
        bottomBar = { ButtonBar(navController) },
        containerColor = WhiteBackground,
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
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            when (val currentState = state) {
                is ServicioBarberoState.Loading -> {
                    CircularProgressIndicator()
                }
                is ServicioBarberoState.Error -> {
                    Text(text = currentState.message, color = Color.Red)
                }
                is ServicioBarberoState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(currentState.servicios, key = { it.id }) {
                            servicio ->
                            CardServicio(servicio = servicio, onDelete = {
                                viewModel.deleteServicio(servicio.id)
                            }) {
                                servicioToEdit = servicio
                                showDialog = true
                            }
                        }
                    }
                }
            }
        }

        if (showDialog) {
            ServicioDialog(
                showDialog = showDialog,
                onDismiss = { showDialog = false },
                onConfirm = { servicio ->
                    if (servicioToEdit == null) {
                        viewModel.addServicio(servicio.nombre, servicio.precio.toDouble(), servicio.duracion)
                    } else {
                        viewModel.updateServicio(servicio.copy(id = servicioToEdit!!.id))
                    }
                    showDialog = false 
                },
                servicioToEdit = servicioToEdit
            )
        }
    }
}
