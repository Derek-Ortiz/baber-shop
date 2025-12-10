package ortiz.derek.c4.barber_shop.data.model

@kotlinx.serialization.Serializable
data class Negocio(
    val id: Int,
    val nombre: String,
    val direccion: String,
    val telefono: String,
    val servicios: List<Servicio>? = null,
    val horarios: List<Horario>? = null
)
