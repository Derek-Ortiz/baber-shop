package ortiz.derek.c4.barber_shop.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity("Negocio",
    foreignKeys = [
        ForeignKey(
            entity = Citas::class,
            parentColumns = ["idCitas"],
            childColumns = ["citas_id"],
            onUpdate = CASCADE
        ),
        ForeignKey(
            entity = Horario::class,
            parentColumns = ["idDia"],
            childColumns = ["dia_id"],
            onUpdate = CASCADE
        )
    ])
data class Negocio(
    @PrimaryKey(true)
    val idNegocio: Int = 0,
    @ColumnInfo("nombre")
    val nombre: String,
    @ColumnInfo("direccion")
    val direccion: String,
    @ColumnInfo("duracion")
    val duracion: String,
    @ColumnInfo("dia_id")
    val idDia: Int,
    @ColumnInfo("citas_id")
    val idCitas: Int
)
