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

data class CitaDto(
    val id: Int,
    val fecha: String,
    val hora: String,
    val estado: String,
    val clienteId: Int,
    val servicioId: Int,
    val negocioId: Int
)

data class ClienteDto(
    val id: Int,
    val nombres: String,
    val apellidoP: String,
    val apellidoM: String,
    val telefono: String,
    val email: String
)
