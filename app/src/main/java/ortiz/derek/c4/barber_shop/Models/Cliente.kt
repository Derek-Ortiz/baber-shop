package ortiz.derek.c4.barber_shop.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cliente")
data class Cliente(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "nombres")
    val nombre: String,
    @ColumnInfo(name = "apellido_p")
    val apellidoP: String,
    @ColumnInfo(name = "apellido_m")
    val apellidoM: String,
    @ColumnInfo(name = "telefono")
    val telefono: Int,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "contrasenia")
    val contrasenia: String,
    @ColumnInfo("direccion")
    val direccion: String
)
