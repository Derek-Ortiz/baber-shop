package ortiz.derek.c4.barber_shop.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity("Servicio",
    foreignKeys = [
        ForeignKey(
            entity = Negocio::class,
            parentColumns = ["idNegocio"],
            childColumns = ["negocio_id"],
            onUpdate = CASCADE
        )
    ])
data class Servicio(
    @PrimaryKey(true)
    val idServicio: Int = 0,
    @ColumnInfo("nombre")
    val nombre: String,
    @ColumnInfo("precio")
    val precio: Float,
    @ColumnInfo("negocio_id")
    val idNegocio: Int
)
