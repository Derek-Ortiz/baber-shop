package ortiz.derek.c4.barber_shop.data.remote.dto

data class CitaCompletaDto(
    val cita: CitaDto,
    val cliente: ClienteDto,
    val negocio: NegocioDto,
    val servicio: ServicioDto
)