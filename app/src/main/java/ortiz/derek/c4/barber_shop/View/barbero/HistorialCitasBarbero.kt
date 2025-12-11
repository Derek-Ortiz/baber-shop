package ortiz.derek.c4.barber_shop.View.barbero

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ortiz.derek.c4.barber_shop.View.barbero.componentes.ButtonBar
import ortiz.derek.c4.barber_shop.View.barbero.componentes.TopBar
import ortiz.derek.c4.barber_shop.data.remote.dto.CitaCompletaDto
import ortiz.derek.c4.barber_shop.ui.theme.WhiteBackground
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun HistorialCitasBarbero(navController: NavController, viewModel: HistorialCitasBarberoViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val state by viewModel.state

    val startDate = remember { mutableStateOf<Date?>(null) }
    val endDate = remember { mutableStateOf<Date?>(null) }

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val onStartDateSet = { _: DatePicker, y: Int, m: Int, d: Int ->
        calendar.set(y, m, d)
        startDate.value = calendar.time
        viewModel.filterCitasByDate(startDate.value, endDate.value)
    }

    val onEndDateSet = { _: DatePicker, y: Int, m: Int, d: Int ->
        calendar.set(y, m, d)
        endDate.value = calendar.time
        viewModel.filterCitasByDate(startDate.value, endDate.value)
    }

    val startDatePickerDialog = DatePickerDialog(context, onStartDateSet, year, month, day)
    val endDatePickerDialog = DatePickerDialog(context, onEndDateSet, year, month, day)

    Scaffold(
        topBar = { TopBar(navController, "Historial de citas") },
        bottomBar = { ButtonBar(navController) },
        containerColor = WhiteBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DateSelector(label = "Inicio", date = startDate.value) { startDatePickerDialog.show() }
                DateSelector(label = "Fin", date = endDate.value) { endDatePickerDialog.show() }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                when (val currentState = state) {
                    is HistorialCitasState.Loading -> {
                        CircularProgressIndicator()
                    }
                    is HistorialCitasState.Error -> {
                        Text(text = currentState.message, color = Color.Red, textAlign = TextAlign.Center)
                    }
                    is HistorialCitasState.Success -> {
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            items(currentState.citas) { cita ->
                                CitaHistorialItem(cita = cita)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DateSelector(label: String, date: Date?, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
        ) {
            Text(text = label, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = date?.let { SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(it) } ?: "dd/mm/yy",
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun CitaHistorialItem(cita: CitaCompletaDto) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Cliente: ${cita.cliente.nombres} ${cita.cliente.apellidoP}", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Servicio: ${cita.servicio.id}")
            Text("Fecha: ${cita.cita.fechaCita}")
            Text("Precio: $${cita.cita.precio}")
            Text("Estado: ${cita.cita.estado}", color = if(cita.cita.estado == "Completada") Color.Green else Color.Red)
        }
    }
}
