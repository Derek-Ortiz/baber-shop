package ortiz.derek.c4.barber_shop.View.barbero.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ortiz.derek.c4.barber_shop.ui.theme.White

@Composable
fun CardDate(name: String, Horario:String){
    Column(
        modifier = Modifier.padding(10.dp)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp))
            .background(White)

    ) {
        Text("Cita: $name")
        Row() {
            Icon(imageVector = Icons.Default.WatchLater,
                "Reloj")
            Text("Horario $Horario")
        }
    }
}