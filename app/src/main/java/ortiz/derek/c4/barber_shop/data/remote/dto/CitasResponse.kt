package ortiz.derek.c4.barber_shop.data.remote.dto

data class CitasResponse(
    val success: Boolean,
    val message: String,
    val data: List<CitaData>
)

data class CitaData(
    val cita: CitaDto,
    val cliente: ClienteDto,
    val negocio: NegocioDto,
    val servicio: ServicioDto
)
