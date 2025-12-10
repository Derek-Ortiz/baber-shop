package ortiz.derek.c4.barber_shop.data.remote.dto

data class GetNegocioResponse(
    val success: Boolean,
    val message: String,
    val data: GetNegocioResponseData
)

data class GetNegocioResponseData(
    val negocio: NegocioDto,
    val horarios: List<HorarioDto>,
    val servicios: List<ServicioDto>
)
