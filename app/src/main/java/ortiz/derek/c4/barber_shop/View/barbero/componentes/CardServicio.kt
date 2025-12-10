package ortiz.derek.c4.barber_shop.View.barbero.componentes

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox
import ortiz.derek.c4.barber_shop.Models.Servicio
import ortiz.derek.c4.barber_shop.ui.theme.PrimaryBlue
import ortiz.derek.c4.barber_shop.ui.theme.White
import ortiz.derek.c4.barber_shop.ui.theme.*

@Composable
fun CardServicio(
    servicio: Servicio,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val animatedCardColor by animateColorAsState(targetValue = if (isPressed) PrimaryBlue else White)
    val animatedTextColor by animateColorAsState(targetValue = if (isPressed) Color.White else Color.Black)
    val animatedPriceColor by animateColorAsState(targetValue = if (isPressed) Yellow else PrimaryBlue)

    val deleteAction = SwipeAction(
        onSwipe = onDelete,
        icon = {
            Icon(
                Icons.Default.Delete,
                contentDescription = "Eliminar servicio",
                modifier = Modifier.padding(16.dp),
                tint = Color.White
            )
        },
        background = Color.Red
    )

    SwipeableActionsBox(
        startActions = listOf(deleteAction),
        endActions = listOf(deleteAction),
        swipeThreshold = 80.dp
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(interactionSource = interactionSource, indication = null, onClick = onClick)
                .background(animatedCardColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = servicio.nombre,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = animatedTextColor
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Schedule, contentDescription = "Duration", tint = animatedTextColor)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "${servicio.duracion} min", color = animatedTextColor)
                    }
                }
                Text(
                    text = "$${servicio.precio}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = animatedPriceColor
                )
            }
        }
    }
}