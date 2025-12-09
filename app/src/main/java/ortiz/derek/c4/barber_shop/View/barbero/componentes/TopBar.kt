package ortiz.derek.c4.barber_shop.View.barbero.componentes

import android.R.attr.id
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ortiz.derek.c4.barber_shop.R
import ortiz.derek.c4.barber_shop.ui.theme.CircleColor
import ortiz.derek.c4.barber_shop.ui.theme.PrimaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController, Text: String) {
    TopAppBar(

        title = {
            Text(
                text = Text,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = CircleColor
        ),
        navigationIcon = {
            Row() {

                IconButton(onClick = {
                    navController.navigate("HomeBarbero")
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.logoarbero),
                        contentDescription = "Barber icon",
                        modifier = Modifier.size(50.dp)
                    )
                }
            }

        }
    )
}


