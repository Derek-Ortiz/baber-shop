package ortiz.derek.c4.barber_shop.View.barbero.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ortiz.derek.c4.barber_shop.data.remote.dto.ServicioDto

@Composable
fun ServicioDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (ServicioDto) -> Unit,
    servicioToEdit: ServicioDto?
) {
    if (showDialog) {
        var nombre by remember { mutableStateOf(servicioToEdit?.nombre ?: "") }
        var precio by remember { mutableStateOf(servicioToEdit?.precio?.toString() ?: "") }
        var duracion by remember { mutableStateOf(servicioToEdit?.duracion?.toString() ?: "") }

        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(if (servicioToEdit == null) "Añadir Servicio" else "Editar Servicio") },
            text = {
                Column {
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = precio,
                        onValueChange = { precio = it },
                        label = { Text("Precio") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = duracion,
                        onValueChange = { duracion = it },
                        label = { Text("Duración (minutos)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val newServicio = (servicioToEdit ?: ServicioDto(0, "", 0f, 0, 0)).copy(
                            nombre = nombre,
                            precio = precio.toFloatOrNull() ?: 0f,
                            duracion = duracion.toIntOrNull() ?: 0
                        )
                        onConfirm(newServicio)
                    }
                ) {
                    Text("Guardar")
                }
            },
            dismissButton = {
                Button(onClick = onDismiss) {
                    Text("Cancelar")
                }
            }
        )
    }
}
