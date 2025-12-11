package ortiz.derek.c4.barber_shop.data.remote.dto

data class CitaDto(
    val id: Int,
    val fechaRealizacion: String?,
    val fechaCita: String,
    val precio: Double,
    val asunto: String,
    val estado: String,
    val clienteId: Int,
    val negocioId: Int,
    val servicioId: Int
)