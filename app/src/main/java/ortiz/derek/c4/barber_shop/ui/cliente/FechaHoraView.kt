package ortiz.derek.c4.barber_shop.ui.cliente

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
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
import ortiz.derek.c4.barber_shop.data.model.CitaRequest
import ortiz.derek.c4.barber_shop.helpers.Result
import ortiz.derek.c4.barber_shop.view_models.FechaHoraViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FechaHoraView(
    navController: NavController,
    servicioId: Int,
    viewModel: FechaHoraViewModel = hiltViewModel()
) {
    val days = (1..31).map { it.toString() }
    val weekDays = listOf("D", "L", "M", "M", "J", "V", "S")
    var selectedDay by remember { mutableStateOf<String?>(null) }
    var selectedTime by remember { mutableStateOf<String?>(null) }
    var showConfirmDialog by remember { mutableStateOf(false) }

    val reservaState by viewModel.reservaState.collectAsState()

    val timeSlots = listOf("8:00", "9:00", "13:00", "15:00")

    LaunchedEffect(reservaState) {
        if (reservaState is Result.Success) {
            navController.navigate("citas")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Seleccionar", color = Color.White, fontSize = 16.sp)
                        Text("Fecha y Hora", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0D47A1))
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFEEEEEE))
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Prev Month */ }) {
                    Icon(Icons.Filled.ArrowLeft, contentDescription = "Prev", modifier = Modifier.size(32.dp), tint = Color(0xFF0D47A1))
                }
                Text("Octubre 2025", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                IconButton(onClick = { /* Next Month */ }) {
                    Icon(Icons.Filled.ArrowRight, contentDescription = "Next", modifier = Modifier.size(32.dp), tint = Color(0xFF0D47A1))
                }
            }

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    weekDays.forEach { day ->
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color(0xFF4292C6), shape = RoundedCornerShape(4.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(day, color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

                 LazyVerticalGrid(
                    columns = GridCells.Fixed(7),
                    modifier = Modifier.height(300.dp),
                    userScrollEnabled = false
                ) {
                    items(2) { 
                         Box(modifier = Modifier.size(40.dp))
                    }
                    items(days) { day ->
                        val isSelected = selectedDay == day
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(40.dp)
                                .background(
                                    if (isSelected) Color(0xFF0D47A1) else Color.White,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable { selectedDay = day },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                day,
                                color = if (isSelected) Color.White else Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Text(
                "Horarios disponibles",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                timeSlots.forEach { time ->
                    val isSelected = selectedTime == time
                    Button(
                        onClick = { selectedTime = time },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) Color(0xFF0D47A1) else Color.White
                        ),
                        shape = RoundedCornerShape(24.dp),
                        border = if (!isSelected) androidx.compose.foundation.BorderStroke(0.dp, Color.Transparent) else null,
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
                    ) {
                        Text(
                            time,
                            color = if (isSelected) Color.White else Color(0xFF0D47A1),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (reservaState is Result.Loading && selectedDay != null && selectedTime != null && showConfirmDialog == false) {
                 
            }

            Button(
                onClick = { if (selectedDay != null && selectedTime != null) showConfirmDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4292C6)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Confirmar cita", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            
            if (reservaState is Result.Error) {
                 Text(
                    text = (reservaState as Result.Error).message,
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        if (showConfirmDialog) {
            ConfirmarCitaDialog(
                fecha = "2025-10-${selectedDay?.padStart(2, '0')}",
                hora = selectedTime ?: "00:00",
                precio = 80,
                onDismiss = { showConfirmDialog = false },
                onConfirm = {
                    showConfirmDialog = false
                    val request = CitaRequest(
                        clienteId = 1,
                        negocioId = 1, 
                        servicioId = servicioId,
                        fecha = "2025-10-${selectedDay?.padStart(2, '0')}",
                        hora = selectedTime ?: "00:00"
                    )
                    viewModel.crearCita(request)
                }
            )
        }
    }
}

@Composable
fun ConfirmarCitaDialog(
    fecha: String,
    hora: String,
    precio: Int,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Detalles de la cita", fontWeight = FontWeight.Bold) },
        text = {
            Column {
                Text("Barber Shop", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text("Ubicación: ", fontWeight = FontWeight.Bold)
                    Text("Calle 60 #123, Centro")
                }
                Row {
                    Text("Servicio: ", fontWeight = FontWeight.Bold)
                    Text("Corte de cabello")
                }
                Row {
                    Text("Fecha: ", fontWeight = FontWeight.Bold)
                    Text(fecha)
                }
                Row {
                    Text("Hora: ", fontWeight = FontWeight.Bold)
                    Text(hora)
                }
                Row {
                    Text("Precio: ", fontWeight = FontWeight.Bold)
                    Text("$$precio")
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4292C6)),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Confirmar", color = Color.White)
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB71C1C)),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Cancelar", color = Color.White)
            }
        },
        containerColor = Color.White,
        shape = RoundedCornerShape(16.dp)
    )
}

@Preview
@Composable
fun FechaHoraViewPreview() {
    val navController = rememberNavController()
    Text("Fecha Hora View Preview")
}
