package ortiz.derek.c4.barber_shop.View.barbero.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ortiz.derek.c4.barber_shop.Models.Servicio
import ortiz.derek.c4.barber_shop.ui.theme.LightBlue


@Composable
fun ServicioDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (Servicio) -> Unit,
    servicioToEdit: Servicio? = null
) {
    if (showDialog) {
        var nombre by remember { mutableStateOf(servicioToEdit?.nombre ?: "") }
        var precio by remember { mutableStateOf(servicioToEdit?.precio?.toString() ?: "") }
        var duracion by remember { mutableStateOf(servicioToEdit?.duracion?.toString() ?: "") }

        Dialog(onDismissRequest = onDismiss) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(
                        text = if (servicioToEdit == null) "Nuevo servicio" else "Editar servicio",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Nombre del servicio", fontWeight = FontWeight.Bold)
                    TextField(value = nombre, onValueChange = { nombre = it }, placeholder = { Text("Nombre") })

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Precio del servicio", fontWeight = FontWeight.Bold)
                    TextField(value = precio, onValueChange = { precio = it }, placeholder = { Text("Precio") })

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Duración del servicio", fontWeight = FontWeight.Bold)
                    TextField(value = duracion, onValueChange = { duracion = it }, placeholder = { Text("Duración") })

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                        Button(
                            onClick = onDismiss,
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                        ) {
                            Text("Cancelar", color = Color.White)
                        }
                        Button(
                            onClick = {
                                val newServicio = Servicio(
                                    id = servicioToEdit?.id ?: 0,
                                    nombre = nombre,
                                    precio = precio.toDoubleOrNull() ?: 0.0,
                                    duracion = duracion.toIntOrNull() ?: 0,
                                    negocioId = servicioToEdit?.negocioId ?: 0
                                )
                                onConfirm(newServicio)
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Text("Confirmar", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
