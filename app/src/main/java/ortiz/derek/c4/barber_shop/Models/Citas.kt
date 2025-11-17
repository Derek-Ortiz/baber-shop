package ortiz.derek.c4.barber_shop.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import java.sql.Date

@Entity (tableName = "citas",
    foreignKeys = [
        ForeignKey(
            entity = Cliente::class,
            parentColumns = ["id"],
            childColumns = ["id_cliente"],
            onDelete = CASCADE
        )
    ])
data class Citas(
    @PrimaryKey(autoGenerate = true)
    val idCitas: Int,
    @ColumnInfo("fecha_realizacion")
    val fechaRealizacion: Date,
    @ColumnInfo("fecha_cita")
    val fechaCita: Date,
    @ColumnInfo("precio")
    val precio: Float,
    @ColumnInfo("asunto")
    val asunto: String,
    @ColumnInfo("id_cliente")
    val idCliente: Int
)
