package ortiz.derek.c4.barber_shop.View.barbero

import android.app.DatePickerDialog
import android.widget.DatePicker
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import ortiz.derek.c4.barber_shop.View.barbero.componentes.CardDate
import ortiz.derek.c4.barber_shop.View.barbero.componentes.TopBar
import ortiz.derek.c4.barber_shop.ui.theme.WhiteBackground
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun HistorialCitasBarbero(navController: NavController, viewModel: HistorialCitasBarberoViewModel = hiltViewModel()) {
    val state by viewModel.state
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val startDate = remember { mutableStateOf<Date?>(null) }
    val endDate = remember { mutableStateOf<Date?>(null) }

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val startDatePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            calendar.set(year, month, dayOfMonth)
            startDate.value = calendar.time
        }, year, month, day
    )

    val endDatePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            calendar.set(year, month, dayOfMonth)
            endDate.value = calendar.time
        }, year, month, day
    )

    Scaffold(
        topBar = { TopBar(navController, "Historial de citas") },
        bottomBar = { ButtonBar(navController) },
        contentColor = WhiteBackground
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            when (val currentState = state) {
                is HistorialCitasState.Loading -> {
                    CircularProgressIndicator()
                }
                is HistorialCitasState.Error -> {
                    Text(text = currentState.message, color = Color.Red)
                }
                is HistorialCitasState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            Text("Barber Shop", color = Color.Black, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                        }
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                DateSelector(label = "Inicio", date = startDate.value) { startDatePickerDialog.show() }
                                DateSelector(label = "Fin", date = endDate.value) { endDatePickerDialog.show() }
                            }
                        }

                        items(currentState.citas) { cita ->
                            CardDate(
                                name = "${cita.cliente.nombres} ${cita.cliente.apellidoP}",
                                Horario = "${cita.cita.hora} - ${cita.cita.fecha}"
                            )
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
