package ortiz.derek.c4.barber_shop.data.remote.dto

data class GetServiciosResponse(
    val success: Boolean,
    val message: String,
    val data: List<ServicioDto>
)
