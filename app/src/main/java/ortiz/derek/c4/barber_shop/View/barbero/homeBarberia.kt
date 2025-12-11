package ortiz.derek.c4.barber_shop.View.barbero

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ortiz.derek.c4.barber_shop.Routes
import ortiz.derek.c4.barber_shop.View.barbero.componentes.ButtonBar
import ortiz.derek.c4.barber_shop.View.barbero.componentes.TopBar
import ortiz.derek.c4.barber_shop.data.remote.dto.GetNegocioResponseData
import ortiz.derek.c4.barber_shop.data.remote.dto.HorarioDto
import ortiz.derek.c4.barber_shop.ui.theme.*
import androidx.compose.material3.ExperimentalMaterial3Api

@Composable
fun BarberiaHome(navController: NavController, viewModel: HomeBarberiaViewModel = hiltViewModel()) {
    val state by viewModel.state

    Scaffold(
        topBar = { TopBar(navController, "Barberia") },
        bottomBar = { ButtonBar(navController) },
        containerColor = WhiteBackground
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            when (val currentState = state) {
                is HomeBarberiaState.Loading -> {
                    CircularProgressIndicator()
                }
                is HomeBarberiaState.Error -> {
                    Text(text = currentState.message, color = Color.Red)
                }
                is HomeBarberiaState.NoNegocio -> {
                    CreateNegocioContent(viewModel = viewModel)
                }
                is HomeBarberiaState.NegocioCreated -> {
                    LaunchedEffect(Unit){
                        navController.navigate(Routes.BarberShopHome.route)
                    }
                }
                is HomeBarberiaState.NegocioLoaded -> {
                    NegocioDetailsContent(negocioData = currentState.negocioData, viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun CreateNegocioContent(viewModel: HomeBarberiaViewModel) {
    var nombre by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Añade tu barbería", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre de la barbería") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                cursorColor = Color.Black,
                focusedBorderColor = LightBlue,
                unfocusedBorderColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = direccion, onValueChange = { direccion = it }, label = { Text("Dirección") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                cursorColor = Color.Black,
                focusedBorderColor = LightBlue,
                unfocusedBorderColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.createNegocio(nombre, direccion) }) {
            Text("Registrar Negocio")
        }
    }
}

@Composable
fun NegocioDetailsContent(negocioData: GetNegocioResponseData, viewModel: HomeBarberiaViewModel) {
    var showHorarioDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(negocioData.negocio.nombreN, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(negocioData.negocio.direccion, fontSize = 16.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("Horarios", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Button(onClick = { showHorarioDialog = true }) {
                Text("Añadir Horario")
            }
        }


        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(negocioData.horarios) {
                HorarioItem(it)
            }
        }

    }

    if (showHorarioDialog) {
        AddHorarioDialog(
            negocioId = negocioData.negocio.id,
            onDismiss = { showHorarioDialog = false },
            onConfirm = {
                dia, horaApertura, horaCierre, _ ->
                viewModel.createHorario(dia, horaApertura, horaCierre)
                showHorarioDialog = false
            }
        )
    }
}

@Composable
fun HorarioItem(horario: HorarioDto) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp), shape = RoundedCornerShape(8.dp)) {
        Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(horario.dia, fontWeight = FontWeight.Bold)
            Text("${horario.horaApertura} - ${horario.horaCierre}")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHorarioDialog(
    negocioId: Int,
    onDismiss: () -> Unit,
    onConfirm: (String, String, String, Int) -> Unit
) {
    var dia by remember { mutableStateOf("") }
    var horaApertura by remember { mutableStateOf("") }
    var horaCierre by remember { mutableStateOf("") }
    val dias = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
    var expanded by remember { mutableStateOf(false) }


    Dialog(onDismissRequest = onDismiss) {
        Card(shape = RoundedCornerShape(16.dp)){
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Añadir Horario", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))

                ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
                    OutlinedTextField(
                        modifier = Modifier.menuAnchor(),
                        readOnly = true,
                        value = dia,
                        onValueChange = {},
                        label = { Text("Día") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
                    )
                    ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        dias.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption) },
                                onClick = {
                                    dia = selectionOption
                                    expanded = false
                                }
                            )
                        }
                    }
                }


                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = horaApertura, onValueChange = { horaApertura = it }, label = { Text("Hora Apertura (HH:mm:ss)") })
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = horaCierre, onValueChange = { horaCierre = it }, label = { Text("Hora Cierre (HH:mm:ss)") })
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancelar")
                    }
                    TextButton(onClick = { onConfirm(dia, horaApertura, horaCierre, negocioId) }) {
                        Text("Confirmar")
                    }
                }
            }
        }

    }
}
