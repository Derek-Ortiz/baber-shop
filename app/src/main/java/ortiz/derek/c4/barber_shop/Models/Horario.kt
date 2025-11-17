package ortiz.derek.c4.barber_shop.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Horario")
data class Horario(
    @PrimaryKey(autoGenerate = true)
    val idDia: Int = 0,
    @ColumnInfo("dia")
    val dia: String,
    @ColumnInfo("hora_apertura")
    val horaApertura: Date,
    @ColumnInfo("hora_cierre")
    val horaCierre: Date
)
