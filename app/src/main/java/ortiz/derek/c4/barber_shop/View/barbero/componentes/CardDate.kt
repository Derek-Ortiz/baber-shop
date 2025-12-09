package ortiz.derek.c4.barber_shop.View.barbero.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortiz.derek.c4.barber_shop.ui.theme.White

@Composable
fun CardDate(name: String, Horario: String) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .background(White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                buildAnnotatedString {
                    append("Cita: ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(name)
                    }
                },
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Schedule,
                    contentDescription = "Reloj"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    buildAnnotatedString {
                        append("Horario ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(Horario)
                        }
                    },
                    fontSize = 16.sp
                )
            }
        }
    }
}