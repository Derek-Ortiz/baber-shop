package ortiz.derek.c4.barber_shop.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import dagger.hilt.android.migration.CustomInject

@Entity("Admin",
    foreignKeys = [
        ForeignKey(
            entity = Negocio::class,
            parentColumns = ["idNegocio"],
            childColumns = ["negocio_id"],
            onUpdate = CASCADE
        )
    ])
data class Admin(
    @PrimaryKey(true)
    val idAdmin: Int = 0,
    @ColumnInfo("nombres")
    val nombres: String,
    @ColumnInfo("apellido_p")
    val apellidoP:String,
    @ColumnInfo("apellido_m")
    val apellidoM:String,
    @ColumnInfo("telefono")
    val telefono: Int,
    @ColumnInfo("email")
    val email:String,
    @ColumnInfo("contrasenia")
    val contrasenia: String,
    @ColumnInfo("negocio_id")
    val idNegocio: Int
)
