package ortiz.derek.c4.barber_shop.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Servicio")
data class Servicio(
    @PrimaryKey(true)
    val idServicio: Int = 0,
    @ColumnInfo("nombre")
    val nombre: String,
    @ColumnInfo("precio")
    val precio: Float,
    @ColumnInfo("id_negocio")
    val idNegocio: Int
)
