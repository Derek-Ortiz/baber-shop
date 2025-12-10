package ortiz.derek.c4.barber_shop.data.model

data class Cita(
    val id: Int,
    val clienteId: Int,
    val negocioId: Int,
    val servicioId: Int,
    val fecha: String,
    val hora: String,
    val estado: String,
    val negocioNombre: String? = null,
    val servicioNombre: String? = null,
    val precio: Double? = null
)
