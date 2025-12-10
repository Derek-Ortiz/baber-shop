package ortiz.derek.c4.barber_shop.View.barbero

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ortiz.derek.c4.barber_shop.R
import ortiz.derek.c4.barber_shop.View.barbero.componentes.ButtonBar
import ortiz.derek.c4.barber_shop.View.barbero.componentes.CardDate
import ortiz.derek.c4.barber_shop.View.barbero.componentes.TopBar
import ortiz.derek.c4.barber_shop.data.remote.dto.GetNegocioResponseData
import ortiz.derek.c4.barber_shop.ui.theme.PrimaryBlue
import ortiz.derek.c4.barber_shop.ui.theme.WhiteBackground

@Composable
fun HomeBarbero(navController: NavController, viewModel: HomeBarberoViewModel = hiltViewModel()) {
    val state by viewModel.state

    Scaffold(
        topBar = { TopBar(navController, "Barberias") },
        bottomBar = { ButtonBar(navController) },
        containerColor = WhiteBackground
    ) {
        innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            when (val currentState = state) {
                is HomeBarberoState.Loading -> {
                    CircularProgressIndicator()
                }
                is HomeBarberoState.Error -> {
                    Text(text = currentState.message, color = Color.Red)
                }
                is HomeBarberoState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            BarberShopInfoCard(
                                negocioData = currentState.negocioData,
                                adminPhone = currentState.adminPhone
                            )
                        }
                        items(currentState.citas) { cita ->
                            CardDate(
                                name = "${cita.cliente.nombres}",
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
fun BarberShopInfoCard(negocioData: GetNegocioResponseData, adminPhone: String?) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(PrimaryBlue.copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logoarbero),
                    contentDescription = "Barber Shop Logo",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(negocioData.negocio?.direccion ?: "Nombre no disponible", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, contentDescription = "Location")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(negocioData.negocio?.direccion ?: "Dirección no disponible")
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Call, contentDescription = "Phone")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(adminPhone ?: "999-123-4567")
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Lock, contentDescription = "Hours")
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        if (negocioData.horarios.isEmpty()){
                            Text("No hay horarios registrados")
                        }else {
                            negocioData.horarios.forEach {
                                Text("${it.dia}: ${it.horaApertura} - ${it.horaCierre}")
                            }
                        }
                    }
                }
            }
        }
    }
}
