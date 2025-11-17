package ortiz.derek.c4.barber_shop.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ortiz.derek.c4.barber_shop.R
import ortiz.derek.c4.barber_shop.ui.theme.CircleColor
import ortiz.derek.c4.barber_shop.ui.theme.LightBlue
import ortiz.derek.c4.barber_shop.ui.theme.PrimaryBlue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registrar(navController: NavController){

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
                    .height(400.dp)
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
                    Text(
                        text = "Registrarse",
                        color = Color.White,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Text(
                        text = "Selecciona un perfil",
                        color = Color.White,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Column(

                        modifier = Modifier.fillMaxWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,


                    ) {

                        Button(
                            onClick = { /* Lógica de iniciar sesión */ },
                            colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Cliente", color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
//                                navController.navigate("Registrar")
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Peluquero", color = Color.White)
                        }
                    }
                }


            }


            Box(
                modifier = Modifier
                    .offset(y = (-500).dp) // Mueve el círculo hacia arriba para superponer
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