package ortiz.derek.c4.barber_shop.ui.cliente

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.first
import ortiz.derek.c4.barber_shop.Routes
import ortiz.derek.c4.barber_shop.data.model.Cita
import ortiz.derek.c4.barber_shop.helpers.Result
import ortiz.derek.c4.barber_shop.view_models.CitasViewModel

@Composable
fun CitasView(navController: NavController, viewModel: CitasViewModel = hiltViewModel()) {
    val citasState by viewModel.citas.collectAsState()

    LaunchedEffect(Unit) {
        val userData = viewModel.userPreferencesRepository.userData.first()
        val clienteId = userData.userId
        if (clienteId != null && clienteId != 0) {
            viewModel.fetchCitas(clienteId)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Mis Citas", fontSize = 20.sp, modifier = Modifier.padding(16.dp))

        when (val state = citasState) {
            is Result.Loading -> CircularProgressIndicator()
            is Result.Success -> {
                val citas = state.data
                citas.forEach { cita ->
                    Text("${cita.servicioNombre ?: "Cita"} - ${cita.fecha}", modifier = Modifier.padding(8.dp))
                }
            }
            is Result.Error -> Text("Error: ${state.message}")
            else -> {}
        }

        Button(onClick = {
            navController.navigate(Routes.Home.route)
        }) {
            Text("Volver al Inicio")
        }
    }
}
