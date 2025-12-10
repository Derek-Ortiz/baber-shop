package ortiz.derek.c4.barber_shop.View.barbero

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ortiz.derek.c4.barber_shop.R
import ortiz.derek.c4.barber_shop.data.remote.dto.RegisterRequest
import ortiz.derek.c4.barber_shop.ui.theme.*

@Composable
fun RegistroBarbero(navController: NavController, viewModel: RegisterViewModel = hiltViewModel()){
    var nombre by remember { mutableStateOf("") }
    var apellidoP by remember { mutableStateOf("") }
    var apellidoM by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("")}
    var email by remember {mutableStateOf("")}
    var contraseña by remember { mutableStateOf("") }
    val registerState by viewModel.registerState
    val userData by viewModel.userPreferencesRepository.userData.collectAsState(initial = null)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp), // Reduced top padding
        contentAlignment = Alignment.Center

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(300.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    //.height(650.dp) // Let height be determined by content
                    .clip(RoundedCornerShape(20.dp))
                    .background(PrimaryBlue)
                    .padding(top = 70.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)

            ) {
                IconButton(
                    onClick ={
                        navController.popBackStack()
                    },
                    modifier = Modifier.align(Alignment.TopStart)
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "regresar",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp))
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()) // Make content scrollable
                ) {
                    // Spacer to push content down from the icon button
                    Spacer(modifier = Modifier.height(40.dp))

                    Text(
                        text = "Registro de Barbero",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = White,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    //Nombre
                    Text(
                        text = "Nombre(s)",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = White,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it},
                        label = { Text("Nombre(s)") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            cursorColor = Color.Black,
                            focusedBorderColor = LightBlue,
                            unfocusedBorderColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    // Apellido paterno
                    Text(
                        text = "Apellido paterno",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = White,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = apellidoP,
                        onValueChange = { apellidoP = it},
                        label = { Text("Apellido paterno") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            cursorColor = Color.Black,
                            focusedBorderColor = LightBlue,
                            unfocusedBorderColor = Color.White
                        )
                    )


                    Spacer(modifier = Modifier.height(8.dp))
                    //Apellido materno
                    Text(
                        text = "Apellido materno",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = White,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = apellidoM,
                        onValueChange = { apellidoM = it},
                        label = { Text("Apellido materno") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            cursorColor = Color.Black,
                            focusedBorderColor = LightBlue,
                            unfocusedBorderColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    //telefono
                    Text(
                        text = "Teléfono",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = White,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = telefono,
                        onValueChange = { telefono = it},
                        label = { Text("Teléfono") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            cursorColor = Color.Black,
                            focusedBorderColor = LightBlue,
                            unfocusedBorderColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    //Correo Electronico
                    Text(
                        text = "Correo Electronico",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = White,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it},
                        label = { Text("Correo Electronico") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            cursorColor = Color.Black,
                            focusedBorderColor = LightBlue,
                            unfocusedBorderColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    //Contraseña
                    Text(
                        text = "Contraseña",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = White,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = contraseña,
                        onValueChange = {contraseña = it},
                        label = { Text("Contraseña") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            cursorColor = Color.Black,
                            focusedBorderColor = LightBlue,
                            unfocusedBorderColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    when (registerState) {
                        is RegisterState.Success -> {
                            LaunchedEffect(Unit) {
                                if (userData?.negocioId == null) {
                                    navController.navigate("homeBarberia") {
                                        popUpTo(navController.graph.startDestinationId) {
                                            inclusive = true
                                        }
                                    }
                                } else {
                                    navController.navigate("homeBarbero") {
                                        popUpTo(navController.graph.startDestinationId) {
                                            inclusive = true
                                        }
                                    }
                                }
                            }
                        }
                        is RegisterState.Error -> {
                            val error = (registerState as RegisterState.Error).message
                            Text(error, color = Color.Red, modifier = Modifier.padding(vertical = 8.dp))
                        }
                        is RegisterState.Loading -> {
                            CircularProgressIndicator(modifier = Modifier.padding(vertical = 8.dp))
                        }
                        is RegisterState.Idle -> {
                           // Do nothing
                        }
                    }

                    Button(
                        onClick = {
                            val registerRequest = RegisterRequest(
                                nombres = nombre,
                                apellidoP = apellidoP,
                                apellidoM = apellidoM,
                                telefono = telefono,
                                email = email,
                                contraseña = contraseña
                            )
                            viewModel.register(registerRequest)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                        modifier = Modifier.fillMaxWidth(),
                        enabled = registerState !is RegisterState.Loading
                    ) {
                        Text("Crear Cuenta", color = Color.White)
                    }
                }
            }


            Box(
                modifier = Modifier
                    .offset(y = (-670).dp) // Adjust offset to position correctly
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(CircleColor)
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logoarbero),
                    contentDescription = "Barber icon",
                    modifier = Modifier.size(100.dp)
                )
            }
        }
    }
}
