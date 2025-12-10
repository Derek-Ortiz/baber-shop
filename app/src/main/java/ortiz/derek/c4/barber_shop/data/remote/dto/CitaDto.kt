package ortiz.derek.c4.barber_shop.data.remote.dto

data class CitaDto(
    val id: Int,
    val fecha: String,
    val hora: String,
    val estado: String,
    val clienteId: Int,
    val servicioId: Int,
    val negocioId: Int
)
