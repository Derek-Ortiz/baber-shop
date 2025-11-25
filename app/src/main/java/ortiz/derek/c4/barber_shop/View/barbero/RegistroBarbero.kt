package ortiz.derek.c4.barber_shop.View.barbero

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ortiz.derek.c4.barber_shop.R
import ortiz.derek.c4.barber_shop.ui.theme.CircleColor
import ortiz.derek.c4.barber_shop.ui.theme.LightBlue
import ortiz.derek.c4.barber_shop.ui.theme.PrimaryBlue
import ortiz.derek.c4.barber_shop.ui.theme.White
import ortiz.derek.c4.barber_shop.ui.theme.SuccessGreen

@Composable
fun RegistroBarbero(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp),
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
                    .height(650.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp))
                    .background(PrimaryBlue)
                    .padding(top = 70.dp, start = 20.dp, end = 20.dp)

            ) {
                IconButton(
                    onClick ={
                        navController.popBackStack()
                    }
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "regresar",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp))
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                        var nombre by remember { mutableStateOf("") }
                        var apellidoP by remember { mutableStateOf("") }
                        var apellidoM by remember { mutableStateOf("") }
                        var telefono by remember { mutableStateOf("")}
                        var email by remember {mutableStateOf("")}
                        var contraseña by remember { mutableStateOf("") }

                        //Nombre
                        Text(
                            text = "Nombre(s)",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = White
                        )
                        TextField(value = nombre, onValueChange = { nombre = it})

                        Spacer(modifier = Modifier.height(8.dp))
                        // Apellido paterno
                        Text(
                            text = "Apellido paterno",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = White
                        )
                        TextField(value = apellidoP, onValueChange = { apellidoP = it})

                        Spacer(modifier = Modifier.height(8.dp))
                        //Aoellido materno
                        Text(
                            text = "Apellido materno",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = White
                        )
                        TextField(value = apellidoM, onValueChange = { apellidoM = it})

                        Spacer(modifier = Modifier.height(8.dp))
                        //telefono
                        Text(
                            text = "Telefono",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = White
                        )
                        TextField(value = telefono, onValueChange = { telefono = it})

                        Spacer(modifier = Modifier.height(8.dp))
                        //Correo Electronico
                        Text(
                            text = "Correo Electronico",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = White
                        )
                        TextField(value = email, onValueChange = { email = it})

                        Spacer(modifier = Modifier.height(8.dp))

                        //Contraseña
                        Text(
                            text = "Contraseña",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = White
                        )
                        TextField(value = contraseña, onValueChange = {contraseña = it})

                        Button(
                            onClick = {
                                navController.navigate("BarberoHome")
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Crear Cuenta", color = Color.White)
                        }
                    }
                }


            }


            Box(
                modifier = Modifier
                    .offset(y = (-700).dp) // Mueve el círculo hacia arriba para superponer
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