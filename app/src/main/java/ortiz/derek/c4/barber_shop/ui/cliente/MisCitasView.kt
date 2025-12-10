package ortiz.derek.c4.barber_shop.ui.cliente

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ortiz.derek.c4.barber_shop.data.model.Cita
import ortiz.derek.c4.barber_shop.helpers.Result
import ortiz.derek.c4.barber_shop.ui.components.BottomNavBar
import ortiz.derek.c4.barber_shop.view_models.MisCitasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisCitasView(
    navController: NavController,
    viewModel: MisCitasViewModel = hiltViewModel()
) {
    val citasState by viewModel.citas.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCitas(1) // Hardcoded user ID for now
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                         Text("Mis citas", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0D47A1))
            )
        },
        bottomBar = { BottomNavBar(navController, currentRoute = "citas") }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFEEEEEE))
        ) {
            when (val state = citasState) {
                is Result.Idle,
                is Result.Loading -> {
                     Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                         CircularProgressIndicator()
                    }
                }
                is Result.Success -> {
                    val citas = state.data
                    val proximasCitas = citas.filter { it.estado == "Confirmada" }
                    val historialCitas = citas.filter { it.estado != "Confirmada" }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            Text("Próximas Citas", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                        if (proximasCitas.isEmpty()) {
                             item { Text("No tienes próximas citas", color = Color.Gray) }
                        } else {
                            items(proximasCitas) { cita ->
                                CitaCard(cita, showCancelButton = true)
                            }
                        }

                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Historial", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                        if (historialCitas.isEmpty()) {
                             item { Text("No hay historial", color = Color.Gray) }
                        } else {
                             items(historialCitas) { cita ->
                                CitaCard(cita, showCancelButton = false)
                            }
                        }
                    }
                }
                is Result.Error -> {
                     Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = state.message,
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CitaCard(cita: Cita, showCancelButton: Boolean) {
    val statusColor = when (cita.estado) {
        "Confirmada" -> Color(0xFF00C853)
        "Completada" -> Color(0xFF00C853)
        "Cancelada" -> Color(0xFFD50000)
        else -> Color.Gray
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(cita.negocioNombre ?: "Barbería", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Surface(
                    color = statusColor,
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = cita.estado,
                        color = Color.White,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            Text("Servicio: ${cita.servicioNombre ?: "Servicio"}", fontSize = 14.sp)
            Text("Fecha: ${cita.fecha}", fontSize = 14.sp)
            Text("Hora: ${cita.hora}", fontSize = 14.sp)
            cita.precio?.let {
                 Text("Precio: $$it", fontSize = 14.sp)
            }

            if (showCancelButton) {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* TODO: Cancel logic via ViewModel */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB71C1C)) // Red
                ) {
                    Text("Cancelar", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Preview
@Composable
fun MisCitasViewPreview() {
    val navController = rememberNavController()
    Text("Mis Citas View Preview")
}
