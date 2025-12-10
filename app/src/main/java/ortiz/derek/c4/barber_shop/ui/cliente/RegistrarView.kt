package ortiz.derek.c4.barber_shop.ui.cliente

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ortiz.derek.c4.barber_shop.R
import ortiz.derek.c4.barber_shop.data.model.Usuario
import ortiz.derek.c4.barber_shop.view_models.RegistrarViewModel
import ortiz.derek.c4.barber_shop.view_models.state.RegistroState

@Composable
fun RegistrarView(
    navController: NavController,
    viewModel: RegistrarViewModel = hiltViewModel()
) {
    var nombre by remember { mutableStateOf("") }
    var apellidoPaterno by remember { mutableStateOf("") }
    var apellidoMaterno by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val registroState by viewModel.registroState.collectAsState()

    DisposableEffect(Unit) {
        onDispose {
            viewModel.resetState()
        }
    }

    LaunchedEffect(registroState) {
        if (registroState is RegistroState.Success) {
            navController.navigate("home") {
                popUpTo("registrar") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier.size(120.dp),
                shape = CircleShape,
                color = Color(0xFF0D47A1)
            ) {
                 Icon(
                     painter = painterResource(id = R.drawable.ic_launcher_foreground),
                     contentDescription = "Logo",
                     tint = Color.White,
                     modifier = Modifier.padding(20.dp)
                 )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF4292C6))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                
                RegisterField(label = "Nombre", value = nombre, onValueChange = { nombre = it }, placeholder = "ej:Juan")
                RegisterField(label = "Apellido Paterno", value = apellidoPaterno, onValueChange = { apellidoPaterno = it }, placeholder = "ej:Perez")
                RegisterField(label = "Apellido Materno", value = apellidoMaterno, onValueChange = { apellidoMaterno = it }, placeholder = "ej:Dominguez")
                RegisterField(label = "Teléfono", value = telefono, onValueChange = { telefono = it }, placeholder = "555-555-55-55", keyboardType = KeyboardType.Phone)
                RegisterField(label = "Dirección", value = direccion, onValueChange = { direccion = it }, placeholder = "ej: calle local num #123")
                RegisterField(label = "Correo electrónico", value = correo, onValueChange = { correo = it }, placeholder = "example@gmail.com", keyboardType = KeyboardType.Email)
                
                Text(text = "Contraseña", color = Color.White, fontSize = 14.sp, modifier = Modifier.padding(top = 8.dp, bottom = 4.dp))
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("***********") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                when (registroState) {
                    is RegistroState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally), color = Color.White)
                    }
                    else -> {
                        Button(
                            onClick = {
                                val usuario = Usuario(
                                    id = 0,
                                    nombre = nombre,
                                    apellidoPaterno = apellidoPaterno,
                                    apellidoMaterno = apellidoMaterno,
                                    telefono = telefono,
                                    direccion = direccion,
                                    email = correo
                                )
                                viewModel.registrarUsuario(usuario, password) 
                            },
                            modifier = Modifier.fillMaxWidth().height(50.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                        ) {
                            Text("Crear cuenta", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
                
                if (registroState is RegistroState.Error) {
                    Text(
                        text = (registroState as RegistroState.Error).message,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RegisterField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = label, color = Color.White, fontSize = 14.sp, modifier = Modifier.padding(bottom = 4.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color.Gray) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )
    }
}

@Preview
@Composable
fun RegistrarViewPreview() {
    val navController = rememberNavController()
    Text("Registrar View Preview")
}
